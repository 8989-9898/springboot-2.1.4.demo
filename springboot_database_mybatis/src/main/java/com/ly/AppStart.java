package com.ly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly
 * @ClassName: AppStart
 * @Author: lin
 * @Description: 启动类
 * @Date: 2019-04-28 10:03
 * @Version: 1.0
 */
@SpringBootApplication
//扫描mapper接口所在的包，或者在接口上添加  @mapper注解也可以。不然会报错
@MapperScan("com.ly.mapper")
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class,args);
    }
}
