package com.ly.entity;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.entity
 * @ClassName: ExceptionEntity
 * @Author: lin
 * @Description: 自定义异常模板类
 * @Date: 2019-05-14 17:24
 * @Version: 1.0
 */
public class ExceptionEntity {

    private Integer code;
    private String message;

    public ExceptionEntity(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
