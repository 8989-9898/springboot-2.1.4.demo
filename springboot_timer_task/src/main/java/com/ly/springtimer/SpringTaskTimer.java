package com.ly.springtimer;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.springtimer
 * @ClassName: SpringTaskTimer
 * @Author: lin
 * @Description: 使用spring的定时任务
 * @Date: 2019-05-14 15:19
 * @Version: 1.0
 */
@Component
public class SpringTaskTimer {


    /**
     * @Scheduled 注解为spring的定时任务注解。包含
     *           cron ：为cron表达式
     *           fixedRate ：为每隔多少时间执行
     *           fixedDelay ： 为上一次任务执行完成后，在隔多少秒执行
     *           initialDelay : 为间隔多长时间执行
     * @Async 注解为异步注解，该注解的作用时可以使任务异步执行
     */

    @Scheduled(cron = "0/1 * * * * *")
    @Async
    public void scheduleTask() {
        System.out.println("使用spring的定时任务，每秒执行一次：" + LocalDateTime.now());
    }

    @Scheduled(fixedRate = 1000)
    public void scheduleTaskRate(){
        System.out.println("使用spring 的定时任务，每隔一秒执行一次："+LocalDateTime.now());
    }

    @Scheduled(fixedDelay = 3000)
    public void scheduleTaskDelay(){
        System.out.println("使用spring的定时任务，在上一次执行完成后3秒在执行："+LocalDateTime.now());
    }
}
