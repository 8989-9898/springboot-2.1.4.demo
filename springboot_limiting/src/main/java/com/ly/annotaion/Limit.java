package com.ly.annotaion;

import com.ly.enums.LimitType;

import java.lang.annotation.*;

/**
 * @ProjectName: springboot-2.1.4.demo
 * @Package: com.ly.annotaion
 * @ClassName: Limit
 * @Author: lin
 * @Description: 限流的注解类
 * @Version: 1.0
 */

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Limit {

    /**
     * 资源的名字
     * @return
     */
    String name() default "";

    /**
     * 资源的key
     * @return
     */
    String key() default "";


    /**
     * key的前缀
     * @return
     */
    String prefix() default "";

    /**
     * 给定的时间段，单位秒
     * @return
     */
    int time();

    /**
     * 最多的访问限制次数
     * @return
     */
    int count();

    /**
     * 类型
     * @return
     */
    LimitType limitType()default LimitType.CUSTOMER;
}


