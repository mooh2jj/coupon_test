package com.dsg.coupon_test.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    ADMIN("어드민"),
    USER("유저");

    private final String description;
}
