package com.ly.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.entity
 * @ClassName: Author
 * @Author: lin
 * @Description: 日期格式转换的第二种方式，更加灵活，优先级最高
 * @Date: 2019-06-11 11:00
 * @Version: 1.0
 */
public class Author {

    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDateTime dateTime;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
