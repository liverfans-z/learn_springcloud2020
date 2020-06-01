package com.jr.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jr.springcloud.alibaba.handler.CustomerBlockHandler;
import com.jr.springcloud.entities.CommonResult;
import com.jr.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: ZhangJR
 * @CreateDate: 2020/5/31 10:51
 */
@RestController
@Slf4j
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handlException")
    public CommonResult byResource(){

        return new CommonResult(200, "按照资源名称测试OK", new Payment(2020L, "serial001"));
    }

    public CommonResult handlException(BlockException block){

        return new CommonResult(444, block.getClass().getCanonicalName() + "服务不可用");
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl",blockHandler = "handlException")
    public CommonResult byUrl()
    {
        return new CommonResult(200,"按url限流测试OK",new Payment(2020L,"serial002"));
    }


    @GetMapping("/rateLimit/customerHandler")
    @SentinelResource(
            value = "customerHandler",
            //自定义的降级类
            blockHandlerClass = CustomerBlockHandler.class,
            //自定义的降级方法
            blockHandler = "handlerException2")
    public CommonResult customerHandler()
    {
        return new CommonResult(200,"自定义限流测试OK",new Payment(2020L,"serial002"));
    }
}
