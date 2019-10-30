package com.ly.springbootmultithreading.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.springbootmultithreading.service
 * @ClassName: ThreadPool
 * @Author: lin
 * @Description: 测试SpringBoot的线程池
 * @Date: 2019-10-30 9:52
 * @Version: 1.0
 */
@Service
public class ThreadPool {

    /**
     * @Async 注解表示该方法是异步方法，使用配置的线程池执行
     * @param i
     */
    @Async
    public void testThreadPool(int i) {
        System.out.println ("线程：" + Thread.currentThread ().getName () + " 执行任务：" + i);
    }

}
