package com.ly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringbootExample03Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootExample03Application.class, args);
    }

    /*用于部署到tomcat时的配置，必须的不然无法启动*/
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringbootExample03Application.class);
    }
}
