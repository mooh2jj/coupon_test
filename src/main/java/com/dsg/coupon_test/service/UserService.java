package com.dsg.coupon_test.service;

import com.dsg.coupon_test.dto.request.RegisterDto;
import com.dsg.coupon_test.exception.CustomApiException;
import com.dsg.coupon_test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.dsg.coupon_test.exception.ResponseCode.DUPLICATED_ID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

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
}
