package com.ly.controller;

import com.ly.pojo.author;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.controller
 * @ClassName: FreeMarkerController
 * @Author: lin
 * @Description: 使用freemaker静态话模板技术
 * @Date: 2019-04-26 11:03
 * @Version: 1.0
 */
@Controller
public class FreeMarkerController {

    /**
     * 使用的是freemarker的静态化模板技术使页面使用方式不一样，其它的都一样
     * @param request
     * @return
     */
    @GetMapping("/findex-request")
    public String demo(HttpServletRequest request){
        request.setAttribute("title","这是一个freemarker模板技术的测试");
        request.setAttribute("description","技术信息freemarker");
        request.setAttribute("author",new author("lin",25,"java炸药"));
        return "findex";
    }

    /**
     * 使用spring 的ModelAndView对象进行数据的保存和跳转操作
     * @return 跳转的页面和数据由spring进行处理
     */
    @GetMapping("/findex-view")
    public ModelAndView demo2(){
        ModelAndView mv=new ModelAndView();
        //用于跳转的页面规则和上面的一样
        mv.setViewName("findex");
        // 保存需要的数据
        mv.addObject("title","这是一个freemarker模板技术的测试");
        mv.addObject("description","技术信息freemarker");
        mv.addObject("author",new author("lin",25,"java炸药"));
        return mv;
    }
}
