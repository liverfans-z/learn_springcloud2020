package com.jr.springcloud.service.impl;

import com.jr.springcloud.dao.PaymentDao;
import com.jr.springcloud.entities.Payment;
import com.jr.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: ZhangJR
 * @CreateDate: 2020/5/20 22:47
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {

        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {

        return paymentDao.getPaymentById(id);
    }
}
