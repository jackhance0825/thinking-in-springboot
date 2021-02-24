package com.jackhance.springboot.error;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 处理整个web controller的异常
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ArithmeticException.class, NullPointerException.class, UserTooManyException.class})  //处理异常
    public String handleArithException(Exception e) {

        log.error("GlobalExceptionHandler 异常是：{}", e);
        return "/login"; //视图地址
    }
}
