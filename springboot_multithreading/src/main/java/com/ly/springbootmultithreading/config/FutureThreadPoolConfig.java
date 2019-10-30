package com.ly.springbootmultithreading.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.springbootmultithreading.config
 * @ClassName: FutureThreadPoolConfig
 * @Author: lin
 * @Description: 创建一个线程池用于有返回值的线程操作
 * @Date: 2019-10-30 13:40
 * @Version: 1.0
 */
@Configuration
public class FutureThreadPoolConfig {

    @Bean
    public Executor futureThreadPool() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor ();
        taskExecutor.setCorePoolSize (10);
        taskExecutor.setMaxPoolSize (50);
        taskExecutor.setQueueCapacity (500);
        taskExecutor.setThreadNamePrefix ("executor-future-");
        taskExecutor.setRejectedExecutionHandler (new ThreadPoolExecutor.CallerRunsPolicy ());
        return taskExecutor;
    }

    @Bean
    public Executor futureThreadPool2() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor ();
        taskExecutor.setCorePoolSize (20);
        taskExecutor.setMaxPoolSize (100);
        taskExecutor.setQueueCapacity (200);
        taskExecutor.setThreadNamePrefix ("my=executor=future=");
        taskExecutor.setRejectedExecutionHandler (new ThreadPoolExecutor.CallerRunsPolicy ());
        return taskExecutor;
    }
}
