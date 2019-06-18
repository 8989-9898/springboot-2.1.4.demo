package com.ly.entity;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.entity
 * @ClassName: Author
 * @Author: lin
 * @Description: 测试实体表
 * @Date: 2019-06-18 11:22
 * @Version: 1.0
 */
public class Author {

    public Integer id;
    public String username;
    public String password;
    public String description;

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Author() {
    }

    public Author(Integer id, String username, String password, String description) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
