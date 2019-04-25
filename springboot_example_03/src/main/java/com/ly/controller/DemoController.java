package com.ly.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.controller
 * @ClassName: DemoController
 * @Author: lin
 * @Description: 测试logback日志管理级别控制的root和logger
 * @Date: 2019-04-25 12:12
 * @Version: 1.0
 */
@RestController
public class DemoController {

    //org.mybatis.logging.Logger

    private Logger logger = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("/demo")
    public Map<String, Object> demo() {
        Map<String, Object> map = new HashMap<>();
        //日志级别从低到高分为TRACE < DEBUG < INFO < WARN < ERROR < FATAL，如果设置为WARN，则低于WARN的信息都不会输出。
        logger.trace("输出trace" + logger.isTraceEnabled());
        if (logger.isTraceEnabled()) {
            map.put("trace", "输出trace");
        }

        logger.debug("输出debug"+logger.isInfoEnabled());
        if (logger.isDebugEnabled()) {
            map.put("debug", "输出debug");
        }
        logger.info(" 输出info"+logger.isInfoEnabled());
        if (logger.isInfoEnabled()) {
            map.put("info","输出info");
        }
        logger.warn("输出warn"+logger.isWarnEnabled());
        if (logger.isWarnEnabled()) {
            map.put("warn","输出warn");
        }
        logger.error("输出error"+logger.isErrorEnabled());
        if (logger.isErrorEnabled()) {
            map.put("error","输出error");
        }
        return map;
    }
}
