package com.ly.pojo;

import lombok.*;

import java.io.Serializable;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.pojo
 * @ClassName: User
 * @Author: lin
 * @Description: 测试redis的缓存类
 * @Date: 2019-04-30 9:27
 * @Version: 1.0
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private int id;
    private String username;
    private String password;
}
