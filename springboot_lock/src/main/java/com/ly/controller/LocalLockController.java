package com.ly.controller;

import com.ly.annotation.LocalLock;
import com.ly.entity.Author;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.controller
 * @ClassName: LocalLockController
 * @Author: lin
 * @Description: 测试防止重复提交的本地锁的接口
 * @Date: 2019-06-10 9:34
 * @Version: 1.0
 */

@RestController
public class LocalLockController {

    @LocalLock(key = "author:args[0]")
    @RequestMapping("/local")
    public Author TestLocalLock(Author author){
        System.out.println(author);
        return author;
    }

}
