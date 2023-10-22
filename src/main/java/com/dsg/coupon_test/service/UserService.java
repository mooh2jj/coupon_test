package com.dsg.coupon_test.service;

import com.dsg.coupon_test.config.security.auth.PrincipalDetails;
import com.dsg.coupon_test.config.security.jwt.JwtUtil;
import com.dsg.coupon_test.dto.request.LoginDto;
import com.dsg.coupon_test.dto.request.RegisterDto;
import com.dsg.coupon_test.entity.User;
import com.dsg.coupon_test.exception.CustomApiException;
import com.dsg.coupon_test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.dsg.coupon_test.exception.ResponseCode.DUPLICATED_ID;
import static com.dsg.coupon_test.exception.ResponseCode.UNAUTHORIZED_CODE;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void register(RegisterDto registerDto) {
        log.info("user service register run...");
        // 1) 동일 유저 네임 중복검사
        userRepository.findByUsername(registerDto.getName())
                .ifPresent(user -> {
                    throw new CustomApiException(DUPLICATED_ID, "이미 존재하는 이름입니다.");
                });

        // 2) 패스워드 인코딩 + 회원가입
        userRepository.save(registerDto.toEntity(passwordEncoder));
    }

    @Transactional
    public String login(LoginDto loginDto) {
        log.info("user service login run...");
        User user = userRepository.findByUsername(loginDto.getName())
                .orElseThrow(() -> new CustomApiException(UNAUTHORIZED_CODE, "존재하지 않는 이름입니다."));
        // 1) 패스워드 일치여부 확인
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new CustomApiException(UNAUTHORIZED_CODE, "패스워드가 일치하지 않습니다.");
        }

        // get accessToken from jwtTokenProvider
        return jwtUtil.generateToken(new PrincipalDetails(user));

    }
}
