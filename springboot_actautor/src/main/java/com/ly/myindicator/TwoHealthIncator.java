package com.ly.myindicator;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: springboot-2.1.4.demo
 * @Package: com.ly.myindicator
 * @ClassName: TwoHealthIncator
 * @Author: lin
 * @Description: 第二种自定义健康检查端点的方法。该种方法的功能更加强大一点
 * @Date: 2019-05-04 15:20
 * @Version: 1.0
 */
@Component
public class TwoHealthIncator extends AbstractHealthIndicator {

    private static final String VERSION="2.0.0";
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.up().withDetail("code",2).withDetail("version",VERSION).up().build();
        //builder.down().withDetail("code",0).withDetail("version",VERSION).down().build();
    }
}
