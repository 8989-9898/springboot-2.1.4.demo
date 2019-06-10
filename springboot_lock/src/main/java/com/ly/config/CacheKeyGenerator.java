package com.ly.config;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.config
 * @ClassName: CacheKeyGenerator
 * @Author: lin
 * @Description: key的生成接口
 * @Date: 2019-06-10 11:13
 * @Version: 1.0
 */
public interface CacheKeyGenerator {

    /**
     * 获取aop参数，生成指定的缓存的key
     * @param point
     * @return key
     */
    String getCacheKey(ProceedingJoinPoint point);
}
