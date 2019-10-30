package com.ly.springbootmultithreading.config;

import com.ly.springbootmultithreading.thread.VisiableThreadPoolExecutor;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.springbootmultithreading.config
 * @className: ThreadConfig
 * @author: lin
 * @Description: 配置SpringBoot 的线程池
 * @Date: 2019-10-30 9:44
 * @Version: 1.0
 */
@Configuration
/**
 * 启动多线程的注解
 */
@EnableAsync
public class ThreadConfig implements AsyncConfigurer {
    /**
     * 实现该方法获取一个线程池
     *
     * @return 返回一个线程池的实例对象
     */
    @Override
    public Executor getAsyncExecutor() {
        //ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor ();

        // 使用 VisiableThreadPoolExecutor 类用于打印线程池信息
        ThreadPoolTaskExecutor taskExecutor = new VisiableThreadPoolExecutor ();
        taskExecutor.setCorePoolSize (10);
        taskExecutor.setMaxPoolSize (20);
        taskExecutor.setQueueCapacity (200);
        taskExecutor.setThreadNamePrefix ("async-service-");
        taskExecutor.setRejectedExecutionHandler (new ThreadPoolExecutor.CallerRunsPolicy ());
        taskExecutor.initialize ();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        System.out.println ("getAsyncUncaughtExceptionHandler");
        return null;
    }
}
