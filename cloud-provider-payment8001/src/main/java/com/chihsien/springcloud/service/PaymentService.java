package com.chihsien.springcloud.service;

import com.chihsien.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @auther chihsien on 2021/5/27
 */
public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
