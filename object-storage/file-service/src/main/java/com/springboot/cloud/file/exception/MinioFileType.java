package com.springboot.cloud.file.exception;

import com.springboot.cloud.common.core.exception.ErrorType;
import lombok.Getter;

/**
 * @author fengdan
 * @date 2021年07月01日 14:39
 */
@Getter
public enum MinioFileType implements ErrorType {
    FILE_UPLOAD_FAILED("040100", "文件上传失败！"),
    FILE_REMOVE_FAILED("040200", "文件删除失败！"),
    FILE_GET_FAILED("040300", "获取文件失败！"),
    FAILED_DOWNLOAD_FILE("040400", "下载文件失败！")

    ;
    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String mesg;

    MinioFileType(String code, String mesg) {
        this.code = code;
        this.mesg = mesg;
    }
}
