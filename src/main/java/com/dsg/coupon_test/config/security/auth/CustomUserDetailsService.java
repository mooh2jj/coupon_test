package com.dsg.coupon_test.config.security.auth;

import com.dsg.coupon_test.entity.User;
import com.dsg.coupon_test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // 시큐리티 로그인이 될때, 시큐리티가 loadUserByUsername 실행, username이 DB에 있는지 확인
    // 있으면 securitycontext 내부 session에 로그인된 세션 저장
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException((username)));

        return new PrincipalDetails(user);
    }
}
