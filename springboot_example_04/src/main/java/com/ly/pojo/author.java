package com.ly.pojo;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.pojo
 * @ClassName: author
 * @Author: lin
 * @Description:
 * @Date: 2019-04-26 9:58
 * @Version: 1.0
 */
public class author {

    private String name;
    private int age;
    private String msg;

    public author(String name, int age, String msg) {
        this.name = name;
        this.age = age;
        this.msg = msg;
    }

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
