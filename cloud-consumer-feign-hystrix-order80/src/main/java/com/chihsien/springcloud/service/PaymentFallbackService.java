package com.chihsien.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @auther chihsien on 2021/5/28
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentHystrixService fallback paymentInfo_OK 555555";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {

        return "PaymentHystrixService fallback paymentInfo_TimeOut 555555QAQ";
    }
}
