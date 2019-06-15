package com.ly.controler;

import com.ly.annotaion.Limit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ProjectName: springboot-2.1.4.demo
 * @Package: com.ly.controler
 * @ClassName: LimitController
 * @Author: lin
 * @Version: 1.0
 */
@RestController
public class LimitController {

    private static final AtomicInteger ATOMIC_INTEGER=new AtomicInteger();


    // 100秒内只可以访问10次
    @Limit(key = "test",time = 100,count = 10)
    @GetMapping("limit")
    public int testLimiter() {
         return ATOMIC_INTEGER.incrementAndGet();
    }
}
