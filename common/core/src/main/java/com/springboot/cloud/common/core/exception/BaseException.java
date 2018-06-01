package com.springboot.cloud.common.core.entity.exception;

import lombok.Getter;

/**
 * Created by zhoutaoo on 2018/5/31.
 */
@Getter
public class BaseException extends RuntimeException {

    private String code;
    private String mesg;

    public BaseException(String code, String mesg, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.mesg = mesg;
    }

    public BaseException(String code, String mesg, String message) {
        super(message);
        this.code = code;
        this.mesg = mesg;
    }

    public BaseException(String code, String mesg) {
        this(code);
        this.mesg = mesg;
    }

    public BaseException(String code) {
        super();
        this.code = code;
    }
}
