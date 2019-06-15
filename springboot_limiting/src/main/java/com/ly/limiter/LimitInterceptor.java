package com.ly.limiter;

import com.google.common.collect.ImmutableList;
import com.ly.annotaion.Limit;
import com.ly.enums.LimitType;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.misc.Launcher;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * @ProjectName: springboot-2.1.4.demo
 * @Package: com.ly.limiter
 * @ClassName: LimitInterceptor
 * @Author: lin
 * @Description: 限流的aop拦截器
 * @Version: 1.0
 */
@Aspect
@Configuration
public class LimitInterceptor {

    private RedisTemplate<String, Serializable> redisTemplate;
    private static final String UNKNOWN = "unknown";

    @Autowired
    public LimitInterceptor(RedisTemplate<String, Serializable> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Around("execution(public * *(..)) && @annotation(com.ly.annotaion.Limit)")
    public Object inteceptor(ProceedingJoinPoint point) {

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Limit limit = method.getAnnotation(Limit.class);
        LimitType limitType = limit.limitType();
        String name = limit.name();
        String key;
        int count = limit.count();
        int time = limit.time();
        switch (limitType) {
            case IP:
                key = getIpAddress();
                break;
            case CUSTOMER:
                key = limit.key();
                break;
            default:
                key = StringUtils.upperCase(method.getName());
                break;
        }
        ImmutableList<String> keys = ImmutableList.of(StringUtils.join(limit.prefix(), key));
        try {
            String lua = buildLuaScript();
            DefaultRedisScript<Number> redisScript = new DefaultRedisScript<>(lua, Number.class);
            Number number = redisTemplate.execute(redisScript, keys, count, time);
            if (number != null && number.intValue() < count) {
                return point.proceed();
            }else {
                // 你被拉入了黑名单
                throw new RuntimeException("You have been dragged into the blacklist");
            }
        } catch (Throwable e) {
            if (e instanceof RuntimeException) {
                throw new RuntimeException(e.getLocalizedMessage());
            }
            throw new RuntimeException("server exception");
        }
    }

    /**
     * lua 脚本
     * @return
     */
    private String buildLuaScript() {
        StringBuffer lua=new StringBuffer();
        lua.append("local c");
        lua.append("\nc = redis.call('get',KEYS[1])");
        // 调用不超过最大值，则直接返回
        lua.append("\nif c and tonumber(c) > tonumber(ARGV[1]) then");
        lua.append("\nreturn c;");
        lua.append("\nend");
        // 执行计算器自加
        lua.append("\nc = redis.call('incr',KEYS[1])");
        lua.append("\nif tonumber(c) == 1 then");
        // 从第一次地哦啊用开始限流，设置对应键值的过期
        lua.append("\nredis.call('expire',KEYS[1],ARGV[2])");
        lua.append("\nend");
        lua.append("\nreturn c;");
        return lua.toString();
    }

    /**
     * 根据 ip 生成key
     * @return
     */
    private String getIpAddress() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip==null || ip.length()==0||UNKNOWN.equalsIgnoreCase(ip)) {
            ip=request.getHeader("Proxy-Client-IP");
        }
        if (ip==null || ip.length()==0||UNKNOWN.equalsIgnoreCase(ip)) {
            ip=request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip==null || ip.length()==0||UNKNOWN.equalsIgnoreCase(ip)) {
            ip=request.getRemoteAddr();
        }
        return ip;
    }
}
