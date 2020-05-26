package com.jr.springcloud.controller;

import com.jr.springcloud.entities.CommonResult;
import com.jr.springcloud.entities.Payment;
import com.jr.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: ZhangJR
 * @CreateDate: 2020/5/20 22:49
 */
@Slf4j
@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("插入结果:" + result);
        if(result > 0){

            return new CommonResult(200,"插入成功！serverPort:" + serverPort,result);
        }else{

            return new CommonResult(444,"插入失败");
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果:" + payment);
        if(payment != null){

            return new CommonResult(200,"查询成功！serverPort:" + serverPort,payment);
        }else{

            return new CommonResult(444,"未查询到数据");
        }
    }

    @RequestMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> a = discoveryClient.getServices();
        for (String element: a) {
            log.info("element:" + element);
        }

        List<ServiceInstance> clientInstances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance ser: clientInstances) {
            log.info(ser.getInstanceId() + "，" + ser.getHost() + "，" + ser.getPort() + "，" + ser.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String timeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}

