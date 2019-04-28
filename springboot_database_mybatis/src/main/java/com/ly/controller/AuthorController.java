package com.ly.controller;

import com.github.pagehelper.PageInfo;
import com.ly.pojo.Author;
import com.ly.server.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.controller
 * @ClassName: AuthorController
 * @Author: lin
 * @Description: 测试mybatis的crud操作
 * @Date: 2019-04-28 13:32
 * @Version: 1.0
 */
@RestController
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping("/author")
    public boolean saveAuthor(Author author){
        return authorService.saveAuthor(author);
    }

    @GetMapping("/author/{pageNum}/{pageSize}")
    public List<Author> findPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize){
        return authorService.findPage(pageNum,pageSize).getList();
    }
}
