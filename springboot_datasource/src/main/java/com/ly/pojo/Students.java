package com.ly.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.rmi.server.UID;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.pojo
 * @ClassName: Students
 * @Author: lin
 * @Description: 测试springboot的data-jpa进行数据库的crud操作
 * @Date: 2019-04-26 16:10
 * @Version: 1.0
 */
// 和数据库对应的表名
@Entity(name = "students")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
// 在返回数据的时候进行转json处理时使用
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Students implements Serializable {

    private static final long serializableIsd=1L;


    // strategy 指定主键生产策略
    // generator 指定主键使用的生成器或者自己编写的序列
    // GenerationType有四个属性 identity  table  sequence  auto
    // identity 主键由数据库自动生成，支持所有的自动增长的数据库
    // table 使用一个特定的数据库表格来保存主键
    // sequence 根据底层数据库的序列来生产主键，条件是数据库支持序列。这个这要和generator一起用，
    // auto 主键有程序控制，是默认值
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int age;
    private String name;
    private String email;
    private String sex;
    // 忽略该字段的映射，不会在表中生产
    @Transient
    private String password;

}
