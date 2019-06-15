package com.ly.repository;

import com.ly.entity.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: springboot-2.1.4.demo
 * @Package: com.ly.repository
 * @ClassName: DBCache
 * @Author: lin
 * @Description: ${description}
 * @Date: ${date} ${time}
 * @Version: 1.0
 */
public class DBCache {

    /**
     * 用户
     */
    public final static Map<String,User> CACHE_USER=new HashMap<>();

    /**
     * 角色
     */
    public final static Map<String,Collection<String>> CACHE_ROLE=new HashMap<>();

    /**
     * 数据
     */
    static {
        CACHE_USER.put("A1",new User(1,"A1","P1","admin",true));
        CACHE_USER.put("A2",new User(2,"A2","P2","admin",false));
        CACHE_USER.put("A3",new User(3,"A3","P3","test",true));

        CACHE_ROLE.put("admin",Arrays.asList("user:list","user:add","user:edit"));
        CACHE_ROLE.put("admin",Arrays.asList("user:list"));
    }


}
