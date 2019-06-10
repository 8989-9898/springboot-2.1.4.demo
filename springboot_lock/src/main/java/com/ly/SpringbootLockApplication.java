package com.ly;

import com.ly.config.CacheKeyGenerator;
import com.ly.config.LockKeyGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootLockApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootLockApplication.class, args);
    }

    @Bean
    public CacheKeyGenerator cacheKeyGenerator(){
        return new LockKeyGenerator();
    }
}
