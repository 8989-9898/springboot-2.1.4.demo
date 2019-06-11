package com.ly.controller;

import com.ly.entity.Author;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.controller
 * @ClassName: DateController
 * @Author: lin
 * @Description: 测试日期格式转换的接口
 * @Date: 2019-06-11 11:14
 * @Version: 1.0
 */
@RestController
public class DateController {

    @RequestMapping("/date")
    public Author author(){
        Author author = new Author();
        author.setDateTime(LocalDateTime.now());
        return author;
    }
}
