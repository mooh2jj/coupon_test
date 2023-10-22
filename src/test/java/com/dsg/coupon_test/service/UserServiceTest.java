package com.dsg.coupon_test.service;

import com.dsg.coupon_test.config.dummy.DummyObject;
import com.dsg.coupon_test.dto.request.RegisterDto;
import com.dsg.coupon_test.entity.User;
import com.dsg.coupon_test.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
class UserServiceTest extends DummyObject {


    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Spy        // @Mock과 다르게 실제 객체를 생성해서 @InjectMocks에 주입해준다.
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void register() {
        // given
        RegisterDto registerDto = RegisterDto.builder()
                .name("test")
                .email("test@test.com")
                .password("1234")
                .build();

        // stub 1
        when(userRepository.findByUsername(any())).thenReturn(Optional.empty());

        // stub 2
        User user = mockUser(1L, "test");
        when(userRepository.save(any())).thenReturn(user);

        // when
        userService.register(registerDto);

        // then
        log.info("user: {}", user);
        assertEquals(user.getName(), registerDto.getName());
        assertEquals(user.getEmail(), registerDto.getEmail());
    }



}