package com.dsg.coupon_test.common;

import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public abstract class PageRequestDto {

    @NotNull
    @Min(1)
    private int page;

    @NotNull
    @Min(1)
    private int size;

    public PageRequestDto() {
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(){
        return PageRequest.of(page -1, size);
    }
}