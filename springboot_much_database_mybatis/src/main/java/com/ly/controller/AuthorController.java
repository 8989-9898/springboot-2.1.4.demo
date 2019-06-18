package com.ly.controller;

import com.github.pagehelper.PageInfo;
import com.ly.entity.Author;
import com.ly.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.controller
 * @ClassName: AuthorController
 * @Author: lin
 * @Date: 2019-06-18 13:20
 * @Version: 1.0
 */
@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/add")
    public int addAuthor(Author author){
        System.out.println(author);
        return authorService.addAuthor(author);
    }


    @GetMapping("/findPage")
    public PageInfo<Author> findAllAuthor(@RequestParam(name = "pageNum",required = false,defaultValue = "1")int pageNum,@RequestParam(name = "pageSize",required = false,defaultValue = "10")int pageSize){
        return authorService.findAllAuthor(pageNum,pageSize);
    }
}
