package com.jr.springcloud.controller;

import com.jr.springcloud.entities.CommonResult;
import com.jr.springcloud.entities.Payment;
import com.jr.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @Description:
 * @Author: ZhangJR
 * @CreateDate: 2020/5/21 20:52
 */
@Slf4j
@RestController
public class OrderController {

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private LoadBalancer myLoadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> clientInstances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(clientInstances == null  || clientInstances.size() <= 0){
            return null;
        }
        ServiceInstance serviceInstance = myLoadBalancer.instances(clientInstances);
        URI uri = serviceInstance.getUri();
        log.info("uri:{}",uri.toString());

        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }

    @GetMapping(value = "/consumer/payment/create")
    public CommonResult create(Payment payment){

        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id")Long id){

        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }
}
