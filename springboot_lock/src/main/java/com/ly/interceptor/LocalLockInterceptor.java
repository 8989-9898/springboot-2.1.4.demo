package com.ly.interceptor;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.ly.annotation.LocalLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.interceptor
 * @ClassName: LocalLockInterceptor
 * @Author: lin
 * @Description: 本地锁的拦截器
 * @Date: 2019-06-10 9:09
 * @Version: 1.0
 */

@Aspect
@Configuration
public class LocalLockInterceptor {

    private static final Cache<String, Object> CACHES = CacheBuilder.newBuilder()
            // 设置缓存最大个数为1000个
            .maximumSize(1000)
            // 设置写缓存后，5秒钟过期
            .expireAfterWrite(5, TimeUnit.SECONDS).build();


    /**
     * 环绕增强
     *
     * @param point
     * @return
     */
    @Around("execution(public * *(..)) && @annotation(com.ly.annotation.LocalLock)")
    public Object interceptor(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        LocalLock lock = method.getAnnotation(LocalLock.class);
        String key = getKey(lock.key(), point.getArgs());
        // 判断key值是否为空
        if (!StringUtils.isEmpty(key)) {
            // 判断key值是否存在
            if (CACHES.getIfPresent(key) != null) {
                throw new RuntimeException("请勿重复提交");
            }
            // 第一次提交，将值保存到缓存中
            CACHES.put(key, method.getName());
        }
        try {
            return point.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException("服务器异常");
        } finally {

        }
    }


    /**
     * key的生成策略
     *
     * @param key  表达式
     * @param args 参数
     * @return 返回的key
     */
    private String getKey(String key, Object args[]) {
        for (int i = 0; i < args.length; i++) {
            key = key.replace("args[" + i + "]", args[i].toString());
        }
        // key================author:Author(id=5, name=12344, gender=男)
        System.out.println("key================" + key);
        return key;
    }
}
