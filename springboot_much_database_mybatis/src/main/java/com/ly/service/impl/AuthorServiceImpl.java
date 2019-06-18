package com.ly.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mapper.AuthorMapper;
import com.ly.service.AuthorService;
import com.ly.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.service.impl
 * @ClassName: AuthorServiceImpl
 * @Author: lin
 * @Description:
 * @Date: 2019-06-18 13:17
 * @Version: 1.0
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorMapper authorMapper;


    @Override
    public int addAuthor(Author author) {
        return authorMapper.insert(author);
    }

    @Override
    public PageInfo<Author> findAllAuthor(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Author> authors = authorMapper.selectAuthors();
        PageInfo<Author> authorPageInfo = new PageInfo<>(authors);
        return authorPageInfo;
    }
}
