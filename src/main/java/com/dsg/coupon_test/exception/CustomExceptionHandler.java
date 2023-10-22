package com.dsg.coupon_test.exception;

import com.dsg.coupon_test.common.dto.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static com.dsg.coupon_test.exception.ResponseCode.*;
import static com.dsg.coupon_test.exception.ResponseCode.INVALID_REQUEST;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    // custom exception
    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> handleCustomAPIException(
            CustomApiException exception,
            WebRequest webRequest
    ) {
        log.error("handleCustomAPIException e", exception);

        return new ResponseEntity<>(ResultDto.fail(INVALID_REQUEST, exception.getErrorMessage(), null), HttpStatus.BAD_REQUEST);
    }


    // global exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(
            Exception exception,
            WebRequest webRequest) {
//        exception.printStackTrace();
//        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(ResultDto.fail(INTERNAL_SERVER_ERROR, exception.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //BindingResult Validation 처리
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        log.error("handleMethodArgumentNotValid", exception);

        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(ResultDto.fail(INVALID_REQUEST, exception.getMessage(), null), HttpStatus.BAD_REQUEST);
    }
}
