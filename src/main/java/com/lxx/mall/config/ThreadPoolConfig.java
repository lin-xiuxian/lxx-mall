package com.lxx.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 林修贤
 * @date 2023/3/20
 * @description
 */
@Configuration
public class ThreadPoolConfig {
    @Bean
    public ExecutorService getThreadPool(){
        return new ThreadPoolExecutor(10, 20, 0l, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    }
}
