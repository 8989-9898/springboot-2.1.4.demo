package com.ly.springbootmultithreading.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.springbootmultithreading.service
 * @ClassName: FutureThreadPoolService
 * @Author: lin
 * @Description: 测试有返回值的线程
 * @Date: 2019-10-30 13:45
 * @Version: 1.0
 */
@Service
public class FutureThreadPoolService {

    @Async
    public void testThread(int i){

        System.out.println ("--------------线程：" + Thread.currentThread ().getName () + " 执行任务：" + i);
    }

    @Async("futureThreadPool")
    public Future<String> doTask1(int i){
        System.out.println ("线程："+Thread.currentThread ().getName ()+" 正在执行 --------> "+i);
        return new AsyncResult<> ("线程："+Thread.currentThread ().getName ()+" 执行 ===========》 "+i +"  完成");
    }

    @Async("futureThreadPool2")
    public Future<String> doTask2(int i){
        System.out.println ("线程："+Thread.currentThread ().getName ()+" 正在执行 --------> "+i);
        return new AsyncResult<> ("线程："+Thread.currentThread ().getName ()+" 执行 ===========》 "+i +"  完成");
    }
}
