package com.ly.controller;

import com.ly.dao.StudentsRepostory;
import com.ly.pojo.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.controller
 * @ClassName: DataJpaController
 * @Author: lin
 * @Description: 使用springboot的data-jpa进行数据库的crud操作
 * @Date: 2019-04-26 16:49
 * @Version: 1.0
 */
@RestController
@RequestMapping("/students/")
public class DataJpaController {

    @Autowired
    public StudentsRepostory repostory;

    @GetMapping("/{name}")
    public List<Students> likeByName(@PathVariable("name") String name){
        // 该方法为自定义的方法，springboot-data-jpa会根据方法名的规则进行sql语句的字段生成进行查询
        return repostory.getFirstByName(name);
    }

    @GetMapping("/{id}")
    public Students getById(@PathVariable("id")Integer id){
        // 该方法是实现的JpaRepository接口来的,获取一条数据
        return repostory.getOne(id);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") Integer id){
        // 删除一条数据，该方法没有返回值，只能通过检查id是否还存在来判断数据是否被删除
        repostory.deleteById(id);
        return repostory.existsById(id);
    }

    @PostMapping
    public Students save(Students students){
        //添加一条数据，数据被添加后会返回回来
        return repostory.save(students);
    }

    @GetMapping("/{pageNum}/{pageSize}")
    public Page<Students> findPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize){
        Pageable page=new PageRequest(pageNum,pageSize,Sort.by(Sort.Order.desc("id")));

        return repostory.findAll(page);
    }



}
