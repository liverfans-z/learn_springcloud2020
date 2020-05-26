package com.jr.springcloud.com.jr.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: ZhangJR
 * @CreateDate: 2020/5/26 22:04
 */
@RestController
@RefreshScope//解决git上配置文件动态更新的问题
public class ConfigClientController {

    //这里一定要注意，这里获取的是远程仓库配置文件中的内容
    //如果你git上的配置文件里没有config.info对应的项，服务就会起不来
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo()
    {
        return configInfo;
    }
}
