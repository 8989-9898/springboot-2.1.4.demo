package com.ly.controller;

import org.apache.shiro.authz.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: springboot-2.1.4.demo
 * @Package: com.ly.controller
 * @ClassName: UserController
 * @Author: lin
 * @Version: 1.0
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("get")
    public String get(){
        return "get.....";
    }

    @RequiresRoles(value = {"admin","test"},logical = Logical.OR)
    @GetMapping("query")
    public String query(){
        return "query...";
    }

    // @RequiresUser
    // @RequiresPermissions 表示需要特定的权限才可以访问  会抛出AuthorizationException
    // @RequiresRoles 表示需要特定的角色才可以访问  会抛出AuthorizationException
    // @RequiresGuest 表示不需要认证就可以访问 同理的就是 /path=anon
    // @RequiresAuthentication 表示登录成功就可以访问，不需要权限和角色


    @RequiresPermissions(value = {"users:query","users:list"},logical = Logical.OR)
    @GetMapping("list")
    public String list(){
        return "list...";
    }


    /**
     * 以下是用初始化数据时添加的权限控制
     * @return
     */
    @GetMapping("find")
    public String find(){
        return "find...";
    }

}
