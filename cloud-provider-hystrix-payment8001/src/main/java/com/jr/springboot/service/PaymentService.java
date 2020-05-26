package com.jr.springboot.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: ZhangJR
 * @CreateDate: 2020/5/24 16:05
 */
@Service
public class PaymentService {
    /**
     * 正常访问肯定OK的方法
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){
        return "线程池:" + Thread.currentThread().getName() + ",paymentInfo_OK" + id + "\t" + "O(∩_∩)O哈哈~";
    }

    //fallbackMethod设置兜底方法。timeoutInMilliseconds设置最大等待时间
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000")
    })
    public String paymentInfo_TimeOut(Integer id){
        int n = 3;
        try {
            TimeUnit.SECONDS.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:" + Thread.currentThread().getName() + ",paymentInfo_TimeOut" + id + "\t" + "耗时" +n+"秒";
    }
    //兜底的方法(好像参数需要和原方法保持一致？未测试)
    public String paymentInfo_TimeOutHandler(Integer id){

        return Thread.currentThread().getName() + ",我是备胎 o(╥﹏╥)o";
    }

    /*************************   服务熔断   ****************************/

    @HystrixCommand(fallbackMethod = "paymentCitcuitBreakerFallback",commandProperties = {
            // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            // 请求次数（10s内，10次访问，有6次失败，就跳闸）
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            // 时间窗口期，时间范围
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            // 失败率达到多少后跳闸
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
    })
    public String paymentCitcuitBreaker(@PathVariable("id")Integer id){
        if(id < 0){
            throw new RuntimeException("id不能是负数！");
        }
        String sn = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + sn;
    }
    public String paymentCitcuitBreakerFallback(@PathVariable("id")Integer id){

        return "ID不能是负数，都他妈降级了";
    }
}
