package com.springboot.cloud.sysadmin.organization.exception;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.web.exception.DefaultGlobalExceptionHandlerAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandlerAdvice extends DefaultGlobalExceptionHandlerAdvice {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public Result userNotFound(UserNotFoundException ex) {
        log.error(ex.getMessage());
        return Result.fail(ex.getErrorType());
    }
}