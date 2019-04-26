package com.ly.controller;

import com.ly.pojo.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.controller
 * @ClassName: JdbcTemplateController
 * @Author: lin
 * @Description: 使用jdbc连接数据库的crud操作
 * @Date: 2019-04-26 14:04
 * @Version: 1.0
 */
@RestController
public class JdbcTemplateController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取所有信息
     *
     * @return 返回表内所以信息
     */
    @GetMapping("/JDBC_author")
    public List<Author> getAll() {
        List<Author> list = jdbcTemplate.query("select * from author;",new Object[]{}, new BeanPropertyRowMapper<>(Author.class));
        return list;
    }

    /**
     * 删除一条数据
     *
     * @param id 使用id
     * @return 删除是否成功
     */
    @DeleteMapping("/JDBC_author/{id}")
    public boolean delete(@PathVariable("id") Integer id) {
        String sql = "delete from author where id = ?";
        int row = jdbcTemplate.update(sql, id);
        return row > 0;
    }

    /**
     * 按照id获取一条数据
     *
     * @param id author id
     * @return 一条数据
     */
    @GetMapping("/JDBC_author/{id}")
    public Author getAuthor(@PathVariable("id") Integer id) {
        Author author = jdbcTemplate.queryForObject("select * from author where id = ?;", new Object[]{id}, new BeanPropertyRowMapper<>(Author.class));
        return author;
    }

    /**
     * 修改一条数据
     * @param author
     * @return 是否成功
     */
    @PutMapping("/JDBC_author")
    public boolean update(Author author) {
        int row = jdbcTemplate.update("update author set username = ? ,password=?,description=? where id=?", author.getUsername(), author.getPassword(), author.getDescription(), author.getId());
        return row > 0;
    }

    /**
     * 添加一条数据
     * @param author
     * @return 是否成功
     */
    @PostMapping("/JDBC_author")
    public boolean save(Author author) {
        int row = jdbcTemplate.update("insert into author (username, password, description) values (?,?,?)", author.getUsername(), author.getPassword(), author.getDescription());
        return row > 0;
    }
}
