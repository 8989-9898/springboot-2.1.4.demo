package com.ly.mapper;

import com.ly.pojo.Author;

import java.util.List;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.mapper
 * @ClassName: AuthorMapping
 * @Author: lin
 * @Description: 数据层接口
 * @Date: 2019-04-28 13:06
 * @Version: 1.0
 */
public interface AuthorMapper {

    int save(Author author);

    List<Author> findPage();

}
