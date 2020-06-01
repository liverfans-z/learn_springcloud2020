package com.jr.springcloud.alibaba.controller;

import com.jr.springcloud.alibaba.domain.CommonResult;
import com.jr.springcloud.alibaba.domain.Order;
import com.jr.springcloud.alibaba.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: ZhangJR
 * @CreateDate: 2020/5/31 21:50
 */
@RestController
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping(value = "/order/create")
    public CommonResult create(Order order){

        orderService.create(order);
        return new CommonResult(200, "创建订单成功！");
    }
}
