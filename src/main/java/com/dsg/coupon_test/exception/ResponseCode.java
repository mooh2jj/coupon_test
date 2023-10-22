package com.dsg.coupon_test.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {

    SUCCESS(200, "성공"),
    INTERNAL_SERVER_ERROR(500, "서버에 오류가 발생했습니다."),

    UNAUTHORIZED_CODE(401, "인증되지 않은 사용자입니다."),
    ROLE_NOT_FOUND(403, "해당되는 권한을 찾을 수 없습니다."),
    EXPIRED_JWT_TOKEN(401, "만료된 토큰입니다."),

    INVALID_REQUEST(400, "잘못된 요청입니다."),
    NO_TARGET(404, "해당되는 대상이 없습니다."),
    DUPLICATED_ID(422, "Id가 중복되어 있습니다."),
    USERNAME_ALREADY_EXISTS(422, "이미 존재하는 회원입니다."),
    AWS_S3_UPLOAD_ERROR(422, "AWS S3 업로드에 실패했습니다."),
    IO_EXCEPTION_CODE(422, "IO Exception이 발생했습니다."),
    ;

    private final Integer code;
    private final String description;

}

