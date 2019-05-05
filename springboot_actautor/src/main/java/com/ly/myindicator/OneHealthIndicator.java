package com.ly.myindicator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: springboot-2.1.4.demo
 * @Package: com.ly.myindicator
 * @ClassName: OneHealthIndicator
 * @Author: lin
 * @Description: 第一种自定义健康检查端点的方法
 * @Date: 2019-05-04 15:07
 * @Version: 1.0
 */

@Component
public class OneHealthIndicator implements HealthIndicator {

    private static final String version = "1.0.0";

    @Override
    public Health health() {
        // down状态
        return Health.down().withDetail("code", 0).withDetail("version", version).down().build();
        // up状态
        //return Health.up().withDetail("code", 1).withDetail("version", version).up().build();
    }
}
