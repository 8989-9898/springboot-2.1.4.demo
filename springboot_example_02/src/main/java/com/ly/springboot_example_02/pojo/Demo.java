package com.ly.springboot_example_02.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.springboot_example_02.pojo
 * @ClassName: Demo
 * @Author: lin
 * @Description: 测试自定义配置文件的属性配置类
 * @Date: 2019-04-24 13:27
 * @Version: 1.0
 */
@Component
//用于获取配置问价中以 my 开头的属性，属性名和字段名相同。也可以在字段上使用 @Value(配置属性全)获取属性
@ConfigurationProperties(prefix = "my1")
public class Demo {

    private String name;
    private int age;
    private String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
