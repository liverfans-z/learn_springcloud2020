package com.jr.springcloud.alibaba.dao;

import com.jr.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description:
 * @Author: ZhangJR
 * @CreateDate: 2020/5/31 21:28
 */
@Mapper
public interface OrderDao {

    //新建订单
    void create(Order order);

    //改订单状态，从0改为1
    void update(@Param("userId") Long userId,@Param("status")Integer status);

}
