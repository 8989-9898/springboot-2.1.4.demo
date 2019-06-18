package com.ly.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.ly.entity.Author;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly
 * @ClassName: AuthorService
 * @Author: lin
 * @Description: 业务层接口
 * @Date: 2019-06-18 13:12
 * @Version: 1.0
 */
public interface AuthorService {

    int addAuthor(Author author);

    PageInfo<Author> findAllAuthor(int pageNum,int pageSize);
}
