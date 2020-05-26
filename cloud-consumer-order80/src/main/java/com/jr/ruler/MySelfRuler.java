package com.jr.ruler;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: ZhangJR
 * @CreateDate: 2020/5/23 21:32
 */
@Configuration
public class MySelfRuler {

    @Bean
    public IRule muRuler(){
        return new RandomRule();
    }
}
