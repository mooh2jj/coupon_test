package com.dsg.coupon_test.dto.request;

import com.dsg.coupon_test.entity.User;
import com.dsg.coupon_test.enums.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class RegisterDto {

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @Builder
    public RegisterDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User toEntity(BCryptPasswordEncoder passwordEncoder) {
        return User.builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(UserRole.USER)
                .build();
    }
}
