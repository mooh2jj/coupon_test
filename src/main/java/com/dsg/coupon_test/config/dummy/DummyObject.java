package com.dsg.coupon_test.config.dummy;

import com.dsg.coupon_test.entity.User;
import com.dsg.coupon_test.enums.UserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class DummyObject {

    protected User newUser(String name) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return User.builder()
                .name(name)
                .email(name + "@test.com")
                .password(passwordEncoder.encode("1234"))
                .role(UserRole.USER)
                .build();
    }

    protected User mockUser(Long id, String name) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return User.builder()
                .id(id)
                .name(name)
                .email(name + "@test.com")
                .password(passwordEncoder.encode("1234"))
                .role(UserRole.USER)
                .build();
    }

}
