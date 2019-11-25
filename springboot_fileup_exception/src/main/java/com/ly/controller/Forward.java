package com.ly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ProjectName: parent
 * @Package: com.ly.controller
 * @ClassName: Forward
 * @Author: lin
 * @Description: HTML转发接口
 * @Date: 2019-10-17 13:20
 * @Version: 1.0
 */
@Controller
public class Forward {

    @GetMapping("/")
    public String forward() {
        return "upload";
    }

    @GetMapping("/resolution")
    public String resolution(){
        return "video-vip-resolution";
    }
}
