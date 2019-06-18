package com.ly.config;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.config
 * @ClassName: DatabaseType
 * @Author: lin
 * @Description: 数据源类型
 * @Date: 2019-06-18 13:52
 * @Version: 1.0
 */
public enum DatabaseType {
    master("write"),slave("read");


    private String name;

    DatabaseType(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DatabaseType{" +
                "name='" + name + '\'' +
                '}';
    }
}
