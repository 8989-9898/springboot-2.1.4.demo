package com.ly.pojo;

import lombok.*;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.com.ly.pojo
 * @ClassName: Author
 * @Author: lin
 * @Description: mybatis测试实体类
 * @Date: 2019-04-28 11:11
 * @Version: 1.0
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    private Integer id;
    private String username;
    private String password;
    private String description;

}
