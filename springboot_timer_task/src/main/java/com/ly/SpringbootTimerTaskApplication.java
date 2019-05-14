package com.ly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTask;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
@EnableAsync   // 使用异步执行
@EnableScheduling  // 使用定时任务
@SpringBootApplication
public class SpringbootTimerTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTimerTaskApplication.class, args);
    }


    /**
     * 配置定时任务线程池的大小
     * @return 新的定时任务线程池，大小为10
     */
    @Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        return scheduler;
    }
}
