package com.ly;

import com.battcn.swagger.annotation.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSwagger2Doc
public class SpringbootSwagger2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSwagger2Application.class, args);
    }

}
