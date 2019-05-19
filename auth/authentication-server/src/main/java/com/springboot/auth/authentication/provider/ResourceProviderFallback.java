package com.springboot.auth.authentication.provider;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.exception.SystemErrorType;

public class ResourceProviderFallback implements ResourceProvider {
    @Override
    public Result resources() {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    @Override
    public Result resources(long userId) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }
}
