package com.ly.server;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mapper.AuthorMapper;
import com.ly.pojo.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.server
 * @ClassName: AuthorService
 * @Author: lin
 * @Description: mybatis操作数据库的事务层
 * @Date: 2019-04-28 13:25
 * @Version: 1.0
 */
@Service
public class AuthorService {
    @Autowired
    private AuthorMapper authorMapper;

    public boolean saveAuthor(Author author){
       return authorMapper.save(author)>0;
    }

    /**
     * 使用分页插件
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Author> findPage(Integer pageNum,Integer pageSize){
        PageInfo<Author> page = PageHelper.startPage(pageNum, pageSize).setOrderBy("id desc").doSelectPageInfo(() -> this.authorMapper.findPage());
        return page;
    }



}
