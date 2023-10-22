package com.dsg.coupon_test.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static com.dsg.coupon_test.common.util.CustomResultUtil.unAuthentication;

@Slf4j
@Configuration
public class SecurityConfig {


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

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.formLogin().disable();
        http.httpBasic().disable();

        // Exception 가로 채기
        http.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
            unAuthentication(response, "인증되지 않은 사용자입니다.");
        });

        http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/api/v1/**").authenticated()
                .antMatchers("/api/admin/**").hasRole("ADMIN") // 최근 공식문서에서는 ROLE_ 접미사 안붙여도 됨
                .anyRequest().permitAll();

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
