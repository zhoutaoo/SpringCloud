package com.springboot.services.producer.exception;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.exception.BaseException;
import com.springboot.cloud.common.core.exception.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {

    @ExceptionHandler(value = {MultipartException.class})
    public Result uploadFileLimitException(MultipartException ex) {
        return Result.fail(ErrorType.UPLOAD_FILE_SIZE_LIMIT, ex.getMessage());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public Result serviceException(MethodArgumentNotValidException ex) {
        return Result.fail(ErrorType.ARGUMENT_NOT_VALID, ex.getMessage());
    }

    @ExceptionHandler(value = {BaseException.class})
    public Result baseException(BaseException ex) {
        return Result.fail(ex.getErrorType(), ex.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exception() {
        return Result.fail();
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result throwable() {
        return Result.fail();
    }
}