package com.dsg.coupon_test.controller;

import com.dsg.coupon_test.dto.request.CouponCreateRequest;
import com.dsg.coupon_test.dto.response.CouponResponse;
import com.dsg.coupon_test.service.AdminCouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/admin/coupon")
@RequiredArgsConstructor
public class CouponController {


    private final AdminCouponService adminCouponService;


    @GetMapping("/list")
    public ResponseEntity<?> list() {
        log.info("coupon list");
        List<CouponResponse> list = adminCouponService.getCouponList();

        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody CouponCreateRequest request
    ) {
        log.info("coupon Create");
        adminCouponService.createCoupon(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("create!");
    }

    ///// coupon record

    // coupon record list
/*    @GetMapping("{couponId}/record/list")
    public ResponseEntity<?> recordList(
            @PathVariable Long couponId
    ) {
        log.info("coupon record list");
        List<CouponRecordResponse> list = adminCouponService.getCouponRecordList();

        return ResponseEntity.ok(list);
    }*/

}
