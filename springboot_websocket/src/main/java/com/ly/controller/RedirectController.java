package com.ly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.controller
 * @ClassName: RedirectController
 * @Author: lin
 * @Description: 页面跳转
 * @Date: 2019-06-10 16:09
 * @Version: 1.0
 */
@Controller
public class RedirectController {

    @RequestMapping("/{url}")
    public String redirect(@PathVariable("url") String url){
        System.out.println(url);
        return url;
    }
}
