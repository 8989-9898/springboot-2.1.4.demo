package com.ly.springboot_example_02.controller;

import com.ly.springboot_example_02.pojo.Demo;
import com.ly.springboot_example_02.pojo.Demo1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.springboot_example_02.controller
 * @ClassName: DemoController
 * @Author: lin
 * @Description: 测试自定义配置文件属性
 * @Date: 2019-04-24 13:25
 * @Version: 1.0
 */
@RestController
public class DemoController {

    Logger log = LoggerFactory.getLogger(DemoController.class);


    private Demo demo;
    private Demo1 demo1;

    @Autowired
    public DemoController(Demo demo, Demo1 demo1) {
        this.demo = demo;
        this.demo1 = demo1;
    }

    @RequestMapping("/demo")
    public Demo getDemo() {
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info(demo.toString());
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        return demo;
    }

    @RequestMapping("/demo1")
    public Demo1 getDemo1() {
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info(demo1.toString());
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        return demo1;
    }


}
