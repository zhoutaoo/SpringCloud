package com.springboot.feign.service;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.exception.ErrorType;
import org.springframework.stereotype.Component;

@Component
public class ClassServiceFallback implements ClassService {
    @Override
    public Result users(String name) {
        return Result.fail(ErrorType.SYSTEM_BUSY);
    }
}
