package com.jr.springcloud.alibaba.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jr.springcloud.entities.CommonResult;

/**
 * @Description:
 * @Author: ZhangJR
 * @CreateDate: 2020/5/31 11:39
 */
public class CustomerBlockHandler {

    /**
     * 必须是static
     * 返回值必须和业务需降级方法一致
     * @param blockException
     * @return
     */
    public static CommonResult handlerException(BlockException blockException){

        return new CommonResult(444, "我自定义的global handler exception--------1");
    }

    public static CommonResult handlerException2(BlockException blockException){

        return new CommonResult(444, "我自定义的global handler exception--------2");
    }
}
