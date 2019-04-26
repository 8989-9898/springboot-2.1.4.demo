package com.ly.controller;

import com.ly.pojo.author;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.controller
 * @ClassName: ThymeleafController
 * @Author: lin
 * @Description: 使用thymeleaf模板技术
 * @Date: 2019-04-26 9:49
 * @Version: 1.0
 */

@Controller
public class ThymeleafController {

    /**
     * 使用简单的方式，使用String作为返回值返回页面
     * @param request 用于保存参数
     * @return String值是需要跳转的页面名称，不需要添加 .html spring默认会自行添加 .html的后缀和自行的默认的templates文件夹中寻找该页面是否存在
     */
    @GetMapping("/index-request")
    public String demo1(HttpServletRequest request){
        request.setAttribute("title","这是一个thymeleaf模板技术的测试thymeleaf");
        request.setAttribute("description","技术信息");
        request.setAttribute("author",new author("lin",25,"java炸药"));
        // 返回的页面
        return "index";
    }


    /**
     * 使用spring 的ModelAndView对象进行数据的保存和跳转操作
     * @return 跳转的页面和数据由spring进行处理
     */
    @GetMapping("/index-view")
    public ModelAndView demo2(){
        ModelAndView mv=new ModelAndView();
        //用于跳转的页面规则和上面的一样
        mv.setViewName("index");
        // 保存需要的数据
        mv.addObject("title","这是一个thymeleaf模板技术的测试");
        mv.addObject("description","技术信息thymeleaf");
        mv.addObject("author",new author("lin",25,"java炸药"));
        return mv;
    }
}
