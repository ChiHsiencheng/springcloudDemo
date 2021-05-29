package com.chihsien.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther chihsien on 2021/5/28
 */
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel()
    {
        //选择最高级别日志FULL
        return Logger.Level.FULL;
    }
}
