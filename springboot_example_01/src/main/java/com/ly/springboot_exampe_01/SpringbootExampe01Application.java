package com.ly.springboot_exampe_01;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@SpringBootApplication
public class SpringbootExampe01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootExampe01Application.class, args);
    }

    @RequestMapping("/demo")
    public String demo(){
        return "hello springboot";
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context){
        return args -> {
            System.out.println("查看springboot默认为我们提供的 Bean");
            String[] names = context.getBeanDefinitionNames();
            Arrays.sort(names);
            Arrays.stream(names).forEach(System.out::println);
        };
    }

}
