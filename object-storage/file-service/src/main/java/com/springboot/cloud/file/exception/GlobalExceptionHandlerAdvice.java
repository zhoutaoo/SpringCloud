package com.springboot.cloud.file.exception;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.web.exception.DefaultGlobalExceptionHandlerAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author fengdan
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandlerAdvice extends DefaultGlobalExceptionHandlerAdvice {

    @ExceptionHandler(value = {MinioFileException.class})
    public Result uploadFileFailure(MinioFileException ex) {
        log.error(ex.getMessage());
        return Result.fail(ex.getErrorType());
    }
}
