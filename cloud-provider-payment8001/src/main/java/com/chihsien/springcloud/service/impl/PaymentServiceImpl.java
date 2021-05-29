package com.chihsien.springcloud.service.impl;

import com.chihsien.springcloud.dao.PaymentDao;
import com.chihsien.springcloud.entities.Payment;
import com.chihsien.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @auther chihsien on 2021/5/27
 */
@Service
public class PaymentServiceImpl implements PaymentService
{
    @Resource
    private PaymentDao paymentDao;


    @Override
    public int create(Payment payment)
    {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id)
    {
        return paymentDao.getPaymentById(id);
    }
}
