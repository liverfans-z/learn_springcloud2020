package com.jr.springcloud.alibaba.service.impl;

import com.jr.springcloud.alibaba.dao.OrderDao;
import com.jr.springcloud.alibaba.domain.Order;
import com.jr.springcloud.alibaba.service.AccountService;
import com.jr.springcloud.alibaba.service.OrderService;
import com.jr.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: ZhangJR
 * @CreateDate: 2020/5/31 21:36
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    @Override
    //name随意
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("------>开始新建订单");
        //1.创建订单
        orderDao.create(order);

        //2.扣减库存
        log.info("------>订单微服务开始调用库存，做扣减start...");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("------>订单微服务开始调用库存，做扣减end");

        //3.扣钱
        log.info("------>订单微服务开始调用账户，扣钱money start...");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("------>订单微服务开始调用账户，扣钱money end");

        //4.修改订单状态
        log.info("------>修改订单状态 start...");
        orderDao.update(order.getUserId(), 0);
        log.info("------>修改订单状态 end");

        log.info("下订单结束 end <------");
    }
}
