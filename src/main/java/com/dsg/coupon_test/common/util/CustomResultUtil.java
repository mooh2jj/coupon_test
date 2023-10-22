package com.dsg.coupon_test.common.util;

import com.dsg.coupon_test.common.dto.ResultDto;
import com.fasterxml.jackson.databind.ObjectMapper;
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
            ResultDto<?> resultDto = ResultDto.builder()
                    .code(401)
                    .message(msg)
                    .data(null)
                    .build();
            String responseBody = mapper.writeValueAsString(resultDto);

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
