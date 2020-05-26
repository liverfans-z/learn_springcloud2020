package com.jr.springcloud.controller;

import com.jr.springcloud.entities.CommonResult;
import com.jr.springcloud.entities.Payment;
import com.jr.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: ZhangJR
 * @CreateDate: 2020/5/24 14:54
 */
@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){

        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String timeout(){
        //openfeign-ribbon默认等待1秒钟

        return paymentFeignService.timeout();
    }
}
