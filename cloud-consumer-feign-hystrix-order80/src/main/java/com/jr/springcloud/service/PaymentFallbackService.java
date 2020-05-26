package com.jr.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: ZhangJR
 * @CreateDate: 2020/5/24 20:24
 */
@Component
public class PaymentFallbackService implements  PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackService paymentInfo_OK";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "PaymentFallbackService paymentInfo_TimeOut";
    }
}
