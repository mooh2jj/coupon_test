package com.dsg.coupon_test.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class SecurityConfigTest {

    @Autowired
    private MockMvc mvc;
    @Test
    public void authentication_test() throws Exception {
        // given
        // when
        ResultActions resultActions = mvc.perform(get("/api/v1/hello"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        int statusCode = resultActions.andReturn().getResponse().getStatus();
        log.info("responseBody: {}", responseBody);
        log.info("statusCode: {}", statusCode);

        //then
        assertThat(statusCode).isEqualTo(401);

    }

    @Test
    public void authorization_test() throws Exception {
        // given
        // when
        ResultActions resultActions = mvc.perform(get("/api/admin/hello"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        int statusCode = resultActions.andReturn().getResponse().getStatus();
        log.info("responseBody: {}", responseBody);
        log.info("statusCode: {}", statusCode);

        //then
        assertThat(statusCode).isEqualTo(403);  // 애도 401

    }
}
