package com.ly.annotation;

import java.lang.annotation.*;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.annotation
 * @ClassName: LocalLock
 * @Author: lin
 * @Description: 防止重复提交的本地锁
 * @Date: 2019-06-10 9:04
 * @Version: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface LocalLock {

    /**
     * 指定key的值
     * @return
     */
    String key() default "";
}
