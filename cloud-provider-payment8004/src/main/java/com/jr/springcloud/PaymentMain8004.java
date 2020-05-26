package com.jr.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description:
 * @Author: ZhangJR
 * @CreateDate: 2020/5/20 22:13
 */
@EnableDiscoveryClient//该注解用于向使用consul或者zookeeper作为注册中心时注册服务
@SpringBootApplication
public class PaymentMain8004 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8004.class, args);
    }
}
