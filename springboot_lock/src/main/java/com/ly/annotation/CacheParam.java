package com.ly.annotation;

import java.lang.annotation.*;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.annotation
 * @ClassName: CacheParam
 * @Author: lin
 * @Description: 锁的参数，用于生成key
 * @Date: 2019-06-10 11:10
 * @Version: 1.0
 */
@Target({ElementType.FIELD,ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheParam {

    /**
     * 字段名称
     * @return
     */
    String name() default "";
}
