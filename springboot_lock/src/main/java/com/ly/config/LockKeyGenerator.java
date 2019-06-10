package com.ly.config;

import com.ly.annotation.CacheLock;
import com.ly.annotation.CacheParam;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.config
 * @ClassName: LockKeyGenerator
 * @Author: lin
 * @Description: 具体的key的生产策略
 * @Date: 2019-06-10 11:22
 * @Version: 1.0
 */

public class LockKeyGenerator implements CacheKeyGenerator {
    @Override
    public String getCacheKey(ProceedingJoinPoint point) {
        MethodSignature signature=(MethodSignature)point.getSignature();
        Method method = signature.getMethod();
        CacheLock lock = method.getAnnotation(CacheLock.class);
        Object[] args = point.getArgs();
        Parameter[] parameters = method.getParameters();
        StringBuffer sf=new StringBuffer();
        for (int i = 0; i < parameters.length; i++) {
            CacheParam param = parameters[i].getAnnotation(CacheParam.class);
            if (param==null) {
                continue;
            }
            sf.append(lock.delimiter()).append(args[i]);
        }
        if (StringUtils.isEmpty(sf.toString())) {
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            for (int i = 0; i < parameterAnnotations.length; i++) {
                Object o = args[i];
                Field[] declaredFields = o.getClass().getDeclaredFields();
                for (Field field : declaredFields) {
                    CacheParam annotation = field.getAnnotation(CacheParam.class);
                    if (annotation==null) {
                        continue;
                    }
                    field.setAccessible(true);
                    sf.append(lock.delimiter()).append(ReflectionUtils.getField(field,o));
                }
            }
        }
        // key==========authors:Author(id=5, name=12344, gender=男)
        return lock.prefix()+sf.toString();
    }
}
