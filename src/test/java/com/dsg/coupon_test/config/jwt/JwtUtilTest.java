package com.dsg.coupon_test.config.jwt;

import com.dsg.coupon_test.config.dummy.DummyObject;
import com.dsg.coupon_test.config.security.auth.PrincipalDetails;
import com.dsg.coupon_test.config.security.jwt.JwtUtil;
import com.dsg.coupon_test.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
public class JwtUtilTest extends DummyObject {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void createToken() {
        // given
        User user = mockUser(1L, "test");
        PrincipalDetails principalDetails = new PrincipalDetails(user);

        // when
        String token = jwtUtil.generateToken(principalDetails);
        // then

        log.info("token: {}", token);
        assertThat(token).isNotNull();
    }

    @Test
    public void getUsernameFromToken() {
        // given
        User user = mockUser(1L, "test");
        PrincipalDetails principalDetails = new PrincipalDetails(user);
        String token = jwtUtil.generateToken(principalDetails);

        // when
        String username = jwtUtil.getUsernameFromJWT(token);

        // then
        log.info("username: {}", username);
        assertThat(username).isEqualTo(user.getName());
    }

    @Test
    public void validateToken() {
        // given
        User user = mockUser(1L, "test");
        PrincipalDetails principalDetails = new PrincipalDetails(user);
        String token = jwtUtil.generateToken(principalDetails);

        // when
        boolean isValid = jwtUtil.validateToken(token);

        // then
        log.info("isValid: {}", isValid);
        assertThat(isValid).isTrue();
    }
}
