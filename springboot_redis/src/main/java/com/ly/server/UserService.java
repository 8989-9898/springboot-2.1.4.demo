package com.ly.server;

import com.ly.pojo.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.server
 * @ClassName: UserService
 * @Author: lin
 * @Description: 测试redis的缓存注解使用
 * @Date: 2019-04-30 15:29
 * @Version: 1.0
 */
@Service
public class UserService {

    /**
     * 查询一条信息
     *
     * @param id 根据id
     * @return
     */
    @Cacheable(value = "user", key = "#id")
    public User getUser(int id) {
        System.out.println("查询一条数据");
        // 从缓存中取不到数据时返回的数据
        return new User(2, "张三", "zs");
    }


    /**
     * 添加或者修改一条信息
     *
     * @param user
     * @return
     */
    @CachePut(value = "user", key = "#user.id")
    public User addOrUpdate(User user) {
        System.out.println("执行修改或者删除");
        return user;
    }


    /**
     * 删除一条信息
     *
     * @param id id
     */
    @CacheEvict(value = "user", key = "#id")
    public void delete(int id) {
        System.out.println("执行删除方法");
    }

    /**
     * 根据条件操作缓存内容并不影响数据库操作，条件表达式返回一个布尔值，true/false，当条件为true，则进行缓存操作，否则直接调用方法执行的返回结果。
     *
     * 长度：@CachePut(value = "user", key = "#user.id",condition = "#user.username.length() < 10")只缓存用户名长度少于10的数据
     * 大小：@Cacheable(value = "user", key = "#id",condition = "#id < 10")只缓存ID小于10的数据
     * 组合：@Cacheable(value="user",key="#user.username.concat(##user.password)")
     * 提前操作：@CacheEvict(value="user",allEntries=true,beforeInvocation=true)加上beforeInvocation=true后，不管内部是否报错，缓存都将被清除，默认情况为false
     * 注解介绍
     *
     * @Cacheable(根据方法的请求参数对其结果进行缓存)
     *
     * key：缓存的 key，可以为空，如果指定要按照 SpEL 表达式编写，如果不指定，则缺省按照方法的所有参数进行组合（如：@Cacheable(value="user",key="#userName")）
     * value：缓存的名称，在 Spring 配置文件中定义，必须指定至少一个（如：@Cacheable(value="user")或者 @Cacheable(value={"user1","use2"})）
     * condition：缓存的条件，可以为空，使用 SpEL 编写，返回 true 或者 false，只有为 true 才进行缓存（如：@Cacheable(value = "user", key = "#id",condition = "#id < 10")）
     *
     * @CachePut(根据方法的请求参数对其结果进行缓存，和@Cacheable不同的是，它每次都会触发真实方法的调用)
     *
     * key：同上
     * value：同上
     * condition：同上
     *
     * @CachEvict(根据条件对缓存进行清空)
     *
     * key：同上
     * value：同上
     * condition：同上
     * allEntries：是否清空所有缓存内容，缺省为 false，如果指定为 true，则方法调用后将立即清空所有缓存（如：@CacheEvict(value = "user", key = "#id", allEntries = true)）
     * beforeInvocation：是否在方法执行前就清空，缺省为 false，如果指定为 true，则在方法还没有执行的时候就清空缓存，缺省情况下，如果方法执行抛出异常，则不会清空缓存（如：@CacheEvict(value = "user", key = "#id", beforeInvocation = true)）
     */



}
