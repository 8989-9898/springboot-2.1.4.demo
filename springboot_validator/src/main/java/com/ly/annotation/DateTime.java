package com.ly.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.annotation
 * @ClassName: DateTime
 * @Author: lin
 * @Description: 自定义的时间格式认证注解
 * @Date: 2019-06-06 16:50
 * @Version: 1.0
 */
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateTimeValidater.class)
public @interface DateTime {

    String message() default "格式错误";  // 提示信息

    String format() default "yyyy-MM-dd";// 定义格式

    Class<?>[] groups() default {};//定义分组

    Class<? extends Payload>[] payload() default {};
}
