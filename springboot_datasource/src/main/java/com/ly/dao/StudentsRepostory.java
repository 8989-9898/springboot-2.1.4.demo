package com.ly.dao;

import com.ly.pojo.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.dao
 * @ClassName: StudentsRepostory
 * @Author: lin
 * @Description: 使用data-jpa进行数据库操作的接口，继承JpaRepository接口，该接口提供了一些默认的数据库操作方法
 * @Date: 2019-04-26 16:54
 * @Version: 1.0
 */
@Component
public interface StudentsRepostory extends JpaRepository<Students,Integer> {


    /**
     * 根据用户名模糊查询
     * @param name
     * @return
     */
    List<Students> getFirstByName(String name);
}
