package com.ly.controller;

import com.ly.annotation.Groups;
import com.ly.entity.Auther;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.controller
 * @ClassName: ValidaterController
 * @Author: lin
 * @Description: 定义数据验证测试接口
 * @Date: 2019-06-06 16:20
 * @Version: 1.0
 */

/**
 * 开启数据检查
 */
@Validated
@RestController
public class ValidaterController {

    @RequestMapping("/object")
    public Auther validaterObject(@Validated Auther auther) {
        return auther;
    }

    @RequestMapping("/charset")
    public String validaterString(@NotBlank(message = "用户姓名不能为空") @Length(max = 5, min = 3, message = "用户名的长度不能大于5小于3") String name) {
        return name;
    }

    /**
     * 根据分组不同实现不同的数据校验
     * @param auther
     * @return
     */
    @RequestMapping("/insert")
    public Auther validaterObjectInsert(@Validated(Groups.Default.class) Auther auther) {
        return auther;
    }
    @RequestMapping("/update")
    public Auther validaterObjectUpdate(@Validated({Groups.Default.class,Groups.Update.class}) Auther auther) {
        return auther;
    }
}
