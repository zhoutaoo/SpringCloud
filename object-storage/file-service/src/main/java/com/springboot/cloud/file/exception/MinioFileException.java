package com.springboot.cloud.file.exception;

import com.springboot.cloud.common.core.exception.BaseException;
import com.springboot.cloud.common.core.exception.ErrorType;

/**
 * @author fengdan
 * @date 2021年07月01日 14:38
 */
public class MinioFileException extends BaseException {
    public MinioFileException() {
        super(MinioFileType.FILE_UPLOAD_FAILED);
    }

    public MinioFileException(String message) {
        super(MinioFileType.FILE_UPLOAD_FAILED, message);
    }

    public MinioFileException(ErrorType errorType, String message) {
        super(errorType, message);
    }
}
