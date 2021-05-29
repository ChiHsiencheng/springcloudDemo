package com.chihsien.springcloud.service;

import com.chihsien.springcloud.entities.CommonResult;
import com.chihsien.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @auther chihsien on 2021/5/28
 * eureka上去找名字叫 CLOUD-PAYMENT-SERVICE 的服务 通过调用地址/payment/get/{id}去调用他的getPaymentById
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService
{
    @GetMapping(value = "/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
}
