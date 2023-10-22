package com.dsg.coupon_test.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginDto {

    private String name;
    private String password;
}
