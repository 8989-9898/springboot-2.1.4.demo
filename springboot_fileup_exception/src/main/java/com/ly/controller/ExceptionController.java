package com.ly.controller;

import com.ly.entity.ExceptionEntity;
import com.ly.exception.CustomException;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.controller
 * @ClassName: ExceptionController
 * @Author: lin
 * @Description: 异常测试控制层
 * @Date: 2019-05-14 17:28
 * @Version: 1.0
 */
@Controller
public class ExceptionController {

    @GetMapping("byZone")
    public Map<String, String> exceptionTest(int num) {
        HashMap<String, String> map = new HashMap<>();
        int sum = 0;
        try {
            sum = 10 / num;
            map.put("code", "200");
            map.put("data", sum + "");
        } catch (Exception e) {
            map.put("code", "500");
            map.put("data", "发生未知异常");
        }
        return map;
    }


    @GetMapping("byNull")
    public Map<String, String> exceptionTest(Integer num) {
        if (num == null) {
            throw new CustomException(500, "参数不能为空！");
        }
        HashMap<String, String> map = new HashMap<>();
        int sum = 10 / num;
        map.put("code", "200");
        map.put("data", sum + "");
        return map;
    }

    /**
     * 自定义异常处理方法,只处理 CustomException异常
     * @param request 请求
     * @param response 返回值
     * @param e 异常
     * @return 自定义的异常包装
     */
    @ExceptionHandler(CustomException.class)
    public ExceptionEntity customExceptionHandler(HttpServletRequest request, HttpServletResponse response,final Exception e){
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        CustomException custom= (CustomException) e;
        return new ExceptionEntity(custom.getCode(),custom.getMessage());
    }

    /**
     * 处理运行时的异常
     * @param request 请求
     * @param response 返回值
     * @param e 自定义的异常封装类
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ExceptionEntity runtimeExceptionHandler(HttpServletRequest request,HttpServletResponse response,final Exception e){
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        RuntimeException runtime= (RuntimeException) e;
        return new ExceptionEntity(400,runtime.getMessage());
    }

}
