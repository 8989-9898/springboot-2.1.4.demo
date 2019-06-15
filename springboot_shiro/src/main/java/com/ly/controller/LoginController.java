package com.ly.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @ProjectName: springboot-2.1.4.demo
 * @Package: com.ly
 * @ClassName: LoginController
 * @Author: lin
 * @Version: 1.0
 */
@RestController
public class LoginController {

    @GetMapping("hello")
    public String hello() {
        return "不用登录就可以访问的页面";
    }

    @GetMapping("index")
    public String index() {
        return "登录成功";
    }


    @GetMapping("error")
    public String error() {
        return "没有权限就不要挣扎了";
    }

    @GetMapping("login")
    public String login(String username, String password, RedirectAttributes model) {

        // 想要获到 SecurityUtils.getSubject() 的对象，访问的地址必须跟 shiro 的拦截地内，否则会报空指针异常
        Subject subject = SecurityUtils.getSubject();

        // 用户输入的用户名和密码保存到 UsernamePasswordToken 中，由shiro的内部对象进行认证对比
        // 认证执行有 AuthRealm 中的doGetAuthenticationInfo 处理
        // 认证成功会继续执行，认证失败抛出异常
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            token.clear();

            return "用户" + username + "不存在";
        } catch (LockedAccountException e) {
            token.clear();

            return "用户" + username + "已被锁定";
        } catch (ExcessiveAttemptsException e) {
            token.clear();

            return "登录次数过多，未验证通过";
        } catch (AuthenticationException e) {
            token.clear();

            return "认证失败";
        }
        return "登录成功";
    }
}
