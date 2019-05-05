package com.ly;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer //激活admin的服务端也代表admin的服务端

public class SpringbootActautorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootActautorApplication.class, args);
    }

}
