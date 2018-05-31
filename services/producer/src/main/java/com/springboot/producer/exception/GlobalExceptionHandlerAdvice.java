package com.springboot.producer.exception;

import com.springboot.cloud.core.entity.Result;
import com.springboot.cloud.core.entity.exception.BaseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {

    @ExceptionHandler(value = {BaseException.class})
    public Result serviceException(BaseException ex) {
        return Result.fail(ex.getCode(), ex.getMesg(), ex.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    public Result exception() {
        return Result.fail();
    }

    @ExceptionHandler(value = {Throwable.class})
    public Result throwable() {
        return Result.fail();
    }
}