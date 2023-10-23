package com.dsg.coupon_test.config.security;

import com.dsg.coupon_test.config.security.jwt.JwtAccessDeniedHandler;
import com.dsg.coupon_test.config.security.jwt.JwtAuthenticationEntryPoint;
import com.dsg.coupon_test.config.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static com.dsg.coupon_test.common.util.CustomResultUtil.forbidden;
import static com.dsg.coupon_test.common.util.CustomResultUtil.unAuthentication;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {


    private final JwtAuthenticationEntryPoint authenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        log.info("security passwordEncoder");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("security config");
        http.headers().frameOptions().disable();
        http.csrf().disable();
        http.cors().configurationSource(corsConfigurationSource());


//        http.exceptionHandling()
//                // 인증 정보 없을 때 401 에러 처리
//                .authenticationEntryPoint(authenticationEntryPoint)
//                // 인가 정보 없을 때 403 에러 처리
//                .accessDeniedHandler(jwtAccessDeniedHandler);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.formLogin().disable();
        http.httpBasic().disable();

        // Exception 가로 채기
        http.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
            unAuthentication(response, authException.getMessage());
        });
        http.exceptionHandling().accessDeniedHandler((request, response, accessDeniedException) -> {
            forbidden(response, accessDeniedException.getMessage());
        });

        http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/api/v1/user/login", "/api/v1/user/register").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/admin/**").authenticated()
                .antMatchers("/api/v1/admin/**").hasRole("ADMIN") // 최근 공식문서에서는 ROLE_ 접미사 안붙여도 됨
                .anyRequest().permitAll();

        // jwt 필터 적용
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    public CorsConfigurationSource corsConfigurationSource() {
        log.info("security cors config");
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");           // 모든 HTTP Method 허용
        configuration.addAllowedOriginPattern("*");    // 모든 IP 주소 허용 -> 나중에 프론트앤드 IP 주소만 허용되게 변경
        configuration.setAllowCredentials(true);       // 클라이언트에게 쿠키 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 경로에 대해 위의 설정 적용
        return source;
    }

}
