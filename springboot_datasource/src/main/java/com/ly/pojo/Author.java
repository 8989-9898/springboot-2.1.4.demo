package com.ly.pojo;

import lombok.*;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.pojo
 * @ClassName: author
 * @Author: lin
 * @Description: 作者实体类
 * @Date: 2019-04-26 14:01
 * @Version: 1.0
 */
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    private Integer id;
    private String username;
    private String password;
    private String description;
}
