package com.ly.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.annotation
 * @ClassName: CacheLock
 * @Author: lin
 * @Description: 防止表单重复提交的分布式注解
 * @Date: 2019-06-10 11:05
 * @Version: 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheLock {

    /**
     *  设置key的前缀
     * @return
     */
    String prefix() default "";

    /**
     * 设置key的过期时间
     * @return
     */
    int expire() default 5;

    /**
     * 设置key的各期时间单位
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 设置key的分割符号
     * @return
     */
    String delimiter() default ":";
}
