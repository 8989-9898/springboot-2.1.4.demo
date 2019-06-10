package com.ly.config;


import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.util.StringUtils;

import java.util.concurrent.*;
import java.util.regex.Pattern;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.config
 * @ClassName: RedisLockHelper
 * @Author: lin
 * @Description: 分布式防止表单重复提交的redis的api的封装操作
 * @Date: 2019-06-10 13:15
 * @Version: 1.0
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisLockHelper {

    private static final String DELIMITER = "|";

    private static final ScheduledExecutorService EXECUTOR_SERVICE = Executors.newScheduledThreadPool(10);
    //ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
    //ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), factory, null);

    private final StringRedisTemplate stringRedisTemplate;

    public RedisLockHelper(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }


    /**
     * 获取锁，（会发生死锁）
     *
     * @param lockKey  键
     * @param value    值
     * @param time     过期时间
     * @param timeUnit 过期时间单位
     * @return true\ false
     */
    public boolean tryLock(String lockKey, String value, long time, TimeUnit timeUnit) {
        Expiration from = Expiration.from(time, timeUnit);
        return stringRedisTemplate.execute((RedisCallback<Boolean>) conncetion -> conncetion.set(lockKey.getBytes(), value.getBytes(), from, RedisStringCommands.SetOption.SET_IF_ABSENT));
    }

    /**
     * 获取锁
     *
     * @param key  key
     * @param uuid 值
     * @param time 过期时间
     * @param unit 时间单位
     * @return true | false
     */
    public boolean lock(String key, String uuid, long time, TimeUnit unit) {
        long milliseconds = Expiration.from(time, unit).getExpirationTimeInMilliseconds();
        Boolean success = stringRedisTemplate.opsForValue().setIfAbsent(key, (System.currentTimeMillis() + milliseconds) + DELIMITER + uuid);
        if (success) {
            Boolean expire = stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
            System.out.println(expire);
        } else {
            String oldValue = stringRedisTemplate.opsForValue().getAndSet(key, (System.currentTimeMillis() + milliseconds) + DELIMITER + uuid);
            String[] oldValues = oldValue.split(Pattern.quote(DELIMITER));
            if (Long.parseLong(oldValues[0]) + 1 <= System.currentTimeMillis()) {
                return true;
            }
        }
        return success;
    }

    /**
     * 直接释放锁
     *
     * @param key  键
     * @param uuid 生成的唯一的uuid
     */
    public void unlock(String key, String uuid) {
        unlock(key, uuid, 0, TimeUnit.MILLISECONDS);
    }

    /**
     * 延迟的释放锁
     *
     * @param key       键
     * @param uuid      生成的唯一的uuid
     * @param delaytime 延迟的时间
     * @param unit      时间单位
     */
    public void unlock(String key, String uuid, long delaytime, TimeUnit unit) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        if (delaytime <= 0) {
            doUnlock(key, uuid);
        } else {
            EXECUTOR_SERVICE.schedule(() -> doUnlock(key, uuid), delaytime, unit);
        }

    }

    /**
     * 具体的释放锁的方法
     *
     * @param key  键
     * @param uuid 生成的唯一的uuid
     */
    private void doUnlock(String key, String uuid) {
        String value = stringRedisTemplate.opsForValue().get(key);
        String[] values = value.split(Pattern.quote(DELIMITER));
        if (values.length <= 0) {
            return;
        }
        if (uuid.equals(values[1])) {
            stringRedisTemplate.delete(key);
        }
    }
}
