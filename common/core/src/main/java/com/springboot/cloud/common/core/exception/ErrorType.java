package com.springboot.cloud.common.core.exception;

import lombok.Getter;

/**
 * Created by zhoutaoo on 2018/6/2.
 */
@Getter
public enum ErrorType {

    SYSTEM_ERROR("-1", "系统异常"),

    SYSTEM_BUSY("000001", "系统繁忙,请稍候再试"),

    ARGUMENT_NOT_VALID("010000", "请求参数校验不通过"),
    UPLOAD_FILE_SIZE_LIMIT("010001", "上传文件大小超过限制");

    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String mesg;

    ErrorType(String code, String mesg) {
        this.code = code;
        this.mesg = mesg;
    }
}
