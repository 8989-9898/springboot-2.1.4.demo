package com.ly.config;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.config
 * @ClassName: DataSourceAspect
 * @Author: lin
 * @Description: 配置多数据源的aop切面类
 * @Date: 2019-06-18 14:51
 * @Version: 1.0
 */
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DataSourceAspect {

    @Before("execution(* com.ly.mapper.*.*(..))")
    public void before(JoinPoint point){
        String className = point.getTarget().getClass().getName();
        String  methodName= point.getSignature().getName();
        String args = StringUtils.join(point.getArgs(), ",");
        System.out.println("类名："+className);
        System.out.println("方法名："+methodName);
        System.out.println("参数名："+args);
        for (DatabaseType type : DatabaseType.values()) {
            // 获取命名规范
            List<String> values = DynamicDataSource.METHOD_TYPE_MAP.get(type);
            for (String value : values) {
                if (methodName.startsWith(value)) {
                    DatabaseContestHolder.setDatabaseType(type);
                    DatabaseType databaseType = DatabaseContestHolder.getDatabaseType();
                    System.out.println("使用的数据源："+type);
                    System.out.println("使用的数据源:"+databaseType);
                    return;
                }
            }
        }
    }


}
