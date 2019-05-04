package com.ly.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * @ProjectName: springboot-2.1.4.demo
 * @Package: com.ly.config
 * @ClassName: ProdSecurityConfig
 * @Author: lin
 * @Description: prod环境下的配置
 * @Date: 2019-05-04 15:55
 * @Version: 1.0
 */
@Configuration
@Profile("prod")
public class ProdSecurityConfig extends WebSecurityConfigurerAdapter {

    private String contextPath;

    public ProdSecurityConfig(AdminServerProperties properties){
        this.contextPath =properties.getContextPath();//获取路径
    }


    @Override
    protected void configure(HttpSecurity security) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler handler=new SavedRequestAwareAuthenticationSuccessHandler();
        handler.setTargetUrlParameter("redirectTo");
        security.authorizeRequests().antMatchers(contextPath+"/assets/**")
                .permitAll().antMatchers(contextPath+"/login").permitAll()
                .anyRequest().authenticated().and().formLogin()
                .loginPage(contextPath+"/login").successHandler(handler).and()
                .logout().logoutUrl(contextPath+"/logout").and().httpBasic().and().csrf().disable();
    }
}
