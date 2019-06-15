package com.ly.entity;

import lombok.*;

import java.io.Serializable;

/**
 * @ProjectName: springboot-2.1.4.demo
 * @Package: com.ly.entity
 * @ClassName: User
 * @Author: lin
 * @Description: ${description}
 * @Date: ${date} ${time}
 * @Version: 1.0
 */

public class User implements Serializable {
    public Integer id;
    public String username;
    public String password;
    public String roleName;// 角色
    public boolean locked;// 是否禁用

    public User() {
    }

    public User(Integer id, String username, String password, String roleName, boolean locked) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleName = roleName;
        this.locked = locked;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roleName='" + roleName + '\'' +
                ", locked=" + locked +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
