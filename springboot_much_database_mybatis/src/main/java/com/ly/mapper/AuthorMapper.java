package com.ly.mapper;

import com.ly.entity.Author;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.mapper
 * @ClassName: AuthorMapper
 * @Author: lin
 * @Description: 数据层接口
 * @Date: 2019-06-18 11:25
 * @Version: 1.0
 */
@Mapper
public interface AuthorMapper {

    int insert(Author author);

    List<Author> selectAuthors();

}
