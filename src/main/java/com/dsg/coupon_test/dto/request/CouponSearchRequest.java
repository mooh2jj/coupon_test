package com.dsg.coupon_test.dto.request;

import com.dsg.coupon_test.common.PageRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CouponSearchRequest extends PageRequestDto {

    @NotBlank
    // 한글만 허용
    @Pattern(regexp = "^[가-힣]*$", message = "한글만 입력 가능합니다.")
    private String name;

    private String code;

    private LocalDate startDate;
    private LocalDate endDate;
}
