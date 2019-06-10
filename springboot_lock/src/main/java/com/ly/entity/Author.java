package com.ly.entity;


import lombok.*;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.entity
 * @ClassName: Author
 * @Author: lin
 * @Description: 测试实体类
 * @Date: 2019-06-10 9:31
 * @Version: 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Author {

    public Integer id;

    public String name;

    public String gender;
}
