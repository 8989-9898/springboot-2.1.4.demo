package com.ly.springbootmultithreading.thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.springbootmultithreading.thread
 * @ClassName: VisiableThreadPoolExecutor
 * @Author: lin
 * @Description: 打印线程信息的类
 * @Date: 2019-10-30 14:15
 * @Version: 1.0
 */
public class VisiableThreadPoolExecutor extends ThreadPoolTaskExecutor {

    /**
     * 打印线程池信息的方法
     *
     * @param prefix 前缀
     */
    private void showThreadPoolInfo(String prefix) {
        ThreadPoolExecutor poolExecutor = getThreadPoolExecutor ();
        if (null == poolExecutor) {
            return;
        }
        System.out.println (this.getThreadNamePrefix () + prefix + " 任务总数：" + poolExecutor.getTaskCount ()
                + " 完成的任务数：" + poolExecutor.getCompletedTaskCount ()
                + " 当前活跃的线程数：" + poolExecutor.getActiveCount ()
                + " 当前等待队列的大小：" + poolExecutor.getQueue ().size ());
    }

    @Override
    public void execute(Runnable task) {
        showThreadPoolInfo (" 1 to execute");
        super.execute (task);
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        showThreadPoolInfo (" 2 to execute");
        super.execute (task, startTimeout);
    }

    @Override
    public Future<?> submit(Runnable task) {
        showThreadPoolInfo (" 1 to submit");
        return super.submit (task);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        showThreadPoolInfo (" 2 to submit");
        return super.submit (task);
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        showThreadPoolInfo (" 1 to submitListenable");
        return super.submitListenable (task);
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        showThreadPoolInfo (" 2 to submitListenable");
        return super.submitListenable (task);
    }
}
