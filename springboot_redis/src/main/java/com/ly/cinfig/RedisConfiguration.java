package com.ly.cinfig;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.cinfig
 * @ClassName: RedisConfiguration
 * @Author: lin
 * @Description: Redis 的配置类，用于配置模板的数据类型
 * @Date: 2019-04-30 9:20
 * @Version: 1.0
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfiguration {


    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory factory){
        RedisTemplate<String,Serializable> template=new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(factory);
        return template;
    }
}
