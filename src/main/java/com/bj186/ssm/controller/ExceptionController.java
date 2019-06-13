package com.bj186.ssm.controller;

import com.bj186.ssm.exception.NullNameException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
    public class ExceptionController {

    @ExceptionHandler(NullNameException.class)
    @ResponseBody
    public NullNameException check(NullNameException e){
        System.out.println("异常被拦截了");
        return e;
    }
}
