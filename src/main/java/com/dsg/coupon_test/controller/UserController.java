package com.dsg.coupon_test.controller;

import com.dsg.coupon_test.common.dto.ResultDto;
import com.dsg.coupon_test.dto.request.LoginDto;
import com.dsg.coupon_test.dto.request.RegisterDto;
import com.dsg.coupon_test.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequestMapping("/api/v1/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody RegisterDto registerDto
    ) {
        log.info("user controller register run...");
        log.info("registerDto: {}", registerDto);
        userService.register(registerDto);

        return new ResponseEntity<>(ResultDto.success(), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody LoginDto loginDto
    ) {
        log.info("user controller login run...");
        log.info("loginDto: {}", loginDto);
        String token = userService.login(loginDto);

        return new ResponseEntity<>(ResultDto.success(token), HttpStatus.OK);
    }
}
