package com.ly.jdktimer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.jdktimer
 * @ClassName: JDKTimer
 * @Author: lin
 * @Description: 使用jdk自带的定时任务，不能用于分布式定时任务
 * @Date: 2019-05-14 14:48
 * @Version: 1.0
 */
public class JDKTimer {

    public static void main(String[] args) {
        // 需要执行的具体任务
        TimerTask task=new TimerTask(){
            @Override
            public void run() {
                System.out.println("自动执行的任务："+ LocalDateTime.now());
            }
        };
        // 任务的执行器。推荐使用线程池
        Timer timer = new Timer();

        // 开始执行任务
        // task 执行的具体任务
        // delay 延持时间，毫秒
        // period 间隔时间，毫秒
        timer.schedule(task,1000,3000);
    }
}
