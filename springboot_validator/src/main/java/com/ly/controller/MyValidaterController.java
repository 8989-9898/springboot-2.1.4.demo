package com.ly.controller;

import com.ly.annotation.DateTime;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.controller
 * @ClassName: MyValidaterController
 * @Author: lin
 * @Description: 自定义的数据认证注解测试
 * @Date: 2019-06-06 17:10
 * @Version: 1.0
 */
@RestController
@Validated
public class MyValidaterController {

    @RequestMapping("/dateTime")
    public String dateTimeTest(@DateTime(message = "数据格式不一致",format = "yyyy-MM-dd HH:mm") String date){
        return date;
    }
}
