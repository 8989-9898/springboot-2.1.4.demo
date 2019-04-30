package com.ly;

import com.ly.pojo.User;
import com.ly.server.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Test
    public void contextLoads() {

        //测试线程安全(未知异常)
        new Thread(() -> {
            int i = 1;
            while (i < 100) {
                stringRedisTemplate.opsForValue().set("add", (++i) + "");
            }
        }).start();
        new Thread(() -> {
            int i = 1;
            while (i < 100) {
                stringRedisTemplate.opsForValue().set("add", (++i) + "");
            }
        }).start();
    }

    @Test
    public void testWhile() {
        int i = 1;
        while (i < 10000) {
            stringRedisTemplate.opsForValue().set("add" + i, (++i) + "");
        }
    }

    @Test
    public void testUser() {
        //测试
        redisTemplate.opsForValue().set("user1", new User(2, "测试", "ceshe"));
        Serializable user1 = redisTemplate.opsForValue().get("user1");
        System.out.println(user1.toString());
    }
            /*
            下列的就是Redis其它类型所对应的操作方式
            **opsForValue：**对应 String（字符串）
            **opsForZSet：**对应 ZSet（有序集合）
            **opsForHash：**对应 Hash（哈希）
            **opsForList：**对应 List（列表）
            **opsForSet：**对应 Set（集合）
            **opsForGeo：**对应 GEO（地理位置）
            */

    @Autowired
    private UserService userService;

    @Test
    public void get() {
        System.out.println(userService.getUser(1));
    }

    @Test
    public void addOrUpdate() {
        userService.addOrUpdate(new User(3, "lisi", "lisi"));
    }

    @Test
    public void del() {
        userService.delete(2);
    }
}
