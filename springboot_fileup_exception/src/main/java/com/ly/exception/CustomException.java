package com.ly.exception;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.exception
 * @ClassName: CustomException
 * @Author: lin
 * @Description: 自定义异常类，基础运行时异常
 * @Date: 2019-05-14 17:17
 * @Version: 1.0
 */
public class CustomException extends RuntimeException {


    private static final long serialVersionUID = 1310343225786014983L;

    private Integer code;

    public CustomException() {    }

    public CustomException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
