package com.ly.controller;

import com.ly.annotation.CacheLock;
import com.ly.annotation.CacheParam;
import com.ly.entity.Author;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.controller
 * @ClassName: CacheLockContrller
 * @Author: lin
 * @Description: 分布式下防止表单重复提交的测试接口
 * @Date: 2019-06-10 14:25
 * @Version: 1.0
 */
@RestController
public class CacheLockContrller {

    @CacheLock(prefix = "authors")
    @PostMapping("cache")
    public Author testCacheLock(@CacheParam(name = "author") Author author) {
        return author;
    }

}
