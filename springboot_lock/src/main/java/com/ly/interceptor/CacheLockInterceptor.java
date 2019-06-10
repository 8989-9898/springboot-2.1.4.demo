package com.ly.interceptor;

import com.ly.annotation.CacheLock;
import com.ly.config.CacheKeyGenerator;
import com.ly.config.RedisLockHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.interceptor
 * @ClassName: CacheLockInterceptor
 * @Author: lin
 * @Description: 分布式下防止表单重复提交的拦截器
 * @Date: 2019-06-10 13:13
 * @Version: 1.0
 */
@Aspect
@Configuration
public class CacheLockInterceptor {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;

    @Autowired
    private RedisLockHelper redisLockHelper;

    @Around("execution(public * *(..)) && @annotation(com.ly.annotation.CacheLock)")
    public Object interceptor(ProceedingJoinPoint point) {

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        CacheLock lock = method.getAnnotation(CacheLock.class);
        if (StringUtils.isEmpty(lock.prefix())) {
            throw new RuntimeException("设置key的前缀");
        }
        String cacheKey = cacheKeyGenerator.getCacheKey(point);
        String uuid = UUID.randomUUID().toString();
        try {

            final boolean success = redisLockHelper.lock(cacheKey,uuid,lock.expire(),lock.timeUnit());
            if (!success) {
                throw new RuntimeException("重复提交");
            }
            try {
                return point.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException("系统异常");
            }
        }finally {
            // 在实际环境中需要打开
            redisLockHelper.unlock(cacheKey,uuid,lock.expire(),lock.timeUnit());
        }
    }
}
