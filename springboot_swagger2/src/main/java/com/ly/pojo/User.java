package com.ly.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.pojo
 * @ClassName: User
 * @Author: lin
 * @Description: 使用swagger2定义类的模板
 * @Date: 2019-04-30 16:35
 * @Version: 1.0
 */
@ApiModel
public class User  implements Serializable {

    @ApiModelProperty("用户主键id")
    private int id;
    @ApiModelProperty("用户名")
    private String name;
    @ApiModelProperty("密码")
    private String password;

    public User() {
    }

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
