package com.ly.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @ProjectName: springboot-2.1.4.demo
 * @Package: com.ly.config
 * @ClassName: DevSecurityConfig
 * @Author: lin
 * @Description: dev环境下的配置
 * @Date: 2019-05-04 15:49
 * @Version: 1.0
 */
@Configuration
@Profile("dev")
public class DevSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.authorizeRequests().anyRequest().permitAll().and().csrf().disable();
    }

}
