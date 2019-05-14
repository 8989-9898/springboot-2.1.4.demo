package com.ly.jdktimer;

import java.time.LocalDateTime;
import java.util.concurrent.*;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.jdktimer
 * @ClassName: ScheduleExecuteServiceTimer
 * @Author: lin
 * @Description: 使用线程池进行定时任务调度
 * @Date: 2019-05-14 15:05
 * @Version: 1.0
 */
public class ScheduleExecuteServiceTimer {
    public static void main(String[] args) {
        ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(10);

        /**
         * 使用线程池执行定时任务调度
         * 1、具体执行的任务
         * 2、延迟的时间
         * 3、间隔的时间
         * 4、时间单位
         */
        executor.scheduleAtFixedRate(() -> System.out.println("使用线程池做定时任务"+ LocalDateTime.now()), 2, 3, TimeUnit.SECONDS);

    }
}
