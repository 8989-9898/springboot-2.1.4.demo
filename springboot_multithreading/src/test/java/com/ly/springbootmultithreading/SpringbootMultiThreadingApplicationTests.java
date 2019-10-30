package com.ly.springbootmultithreading;

import com.ly.springbootmultithreading.service.FutureThreadPoolService;
import com.ly.springbootmultithreading.service.ThreadPool;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMultiThreadingApplicationTests {

    @Autowired
    private ThreadPool threadPool;

    @Autowired
    private FutureThreadPoolService futureThreadPoolService;

    @Test
    public void testOne() {
        System.out.println ("===========");
    }

    @Test
    public void testThreadPool() {
        for (int i = 0; i < 50; i++) {
            threadPool.testThreadPool ((i + 1));
            try {
                Thread.sleep (20);
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
        }
    }

    @Test
    public void testFutureThreadPool() throws ExecutionException, InterruptedException {
        List<Future<String>> task1 = new ArrayList<> ();
        List<Future<String>> task2 = new ArrayList<> ();
        for (int i = 0; i < 100; i++) {
            task1.add (futureThreadPoolService.doTask1 (i));
            task2.add (futureThreadPoolService.doTask2 (i));
        }
        for (int i = 0; i < 100; i++) {
            if (!task1.get (i).isDone () || task2.get (i).isDone ()) {
                continue;
            }
        }
        for (int i = 0; i < 100; i++) {
            System.out.println (task1.get (i).get ());
            System.out.println (task2.get (i).get ());
        }

    }
}
