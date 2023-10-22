package com.dsg.coupon_test.common.util;

import com.dsg.coupon_test.common.dto.ResultDto;
import com.dsg.coupon_test.exception.ResponseCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class CustomResultUtil {

    public static void unAuthentication(HttpServletResponse response, String msg) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            ResultDto<Object> resultDto = ResultDto.fail(ResponseCode.UNAUTHORIZED_CODE, msg, null);
            String responseBody = mapper.registerModule(new JavaTimeModule()).writeValueAsString(resultDto);

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().println("{ \"error\": \"" + authException.getMessage() + "\" }");
            response.getWriter().println(responseBody);
        } catch (IOException e) {
            log.error("unAuthentication error", e);
        }

    }

}
