package com.ly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 使用多数据源时不需要配置mapper的扫描地址
// @MapperScan("com.ly.mapper")
public class SpringbootMuchDatabaseMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMuchDatabaseMybatisApplication.class, args);
    }

}
