package com.ly.exception;

import com.ly.entity.ExceptionEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.exception
 * @ClassName: MyExceptionHandler
 * @Author: lin
 * @Description: 定义全局异常，继承ResponseEntityExceptionHandler
 * @Date: 2019-05-14 17:46
 * @Version: 1.0
 */
public class MyExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException m= (MethodArgumentNotValidException) ex;
            return new ResponseEntity<>(new ExceptionEntity(status.value(),m.getMessage()),status);
        }
        if (ex instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException m= (MethodArgumentTypeMismatchException) ex;
            return new ResponseEntity<>(new ExceptionEntity(status.value(),"抛出异常信息，注意点"),status);
        }
        return new ResponseEntity<>(new ExceptionEntity(status.value(),"没有处理的异常信息"),status);
    }
}
