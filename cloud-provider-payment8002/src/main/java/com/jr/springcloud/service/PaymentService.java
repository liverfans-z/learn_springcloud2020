package com.jr.springcloud.service;

import com.jr.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @Description:
 * @Author: ZhangJR
 * @CreateDate: 2020/5/20 22:46
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id")Long id);
}
