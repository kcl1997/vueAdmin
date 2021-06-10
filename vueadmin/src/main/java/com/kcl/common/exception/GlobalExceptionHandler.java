package com.kcl.common.exception;

import com.kcl.common.out.Result;

import org.springframework.data.redis.RedisSystemException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.common.exception
 * 文件名   GlobalExceptionHandler
 * 创建者
 * 创建时间: 2021/6/3 11:13 PM
 * 描述  全局异常处理类
 */


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    //最大的错误 运行时异常
    @ResponseStatus(HttpStatus.BAD_REQUEST) //状态码
    @ExceptionHandler(value = RuntimeException.class)
    public Result runtimeExceptionHandler(RuntimeException e){
        log.error("RuntimeException异常---------------------");
        e.printStackTrace();
        return Result.error(e.getMessage());
    }

    //实体类约束异常
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        //resp.setStatus(401);
        log.error("实体类约束异常,MethodArgumentNotValidException---------------------");
        String defaultMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        return Result.error(defaultMessage);
    }

    //参数转换异常
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result illegalArgumentExceptionHandler(IllegalArgumentException e){
        log.error("Assert异常---------------------");
        return Result.error(e.getMessage());
    }


    //RedisSystemException
    @ExceptionHandler(value = RedisSystemException.class)
    public Result redisSystemExceptionHandler(RedisSystemException e){
        log.error("RedisSystemException---------------------");
        return Result.error(e.getMessage());
    }
}

