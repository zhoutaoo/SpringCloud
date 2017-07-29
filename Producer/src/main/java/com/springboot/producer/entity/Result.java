package com.springboot.producer.entity;


public class Result<T> {

    public static final String SUCCESSFUL_CODE = "000000";
    public static final String SUCCESSFUL_MESG = "处理成功";
    public static final String ERROR = "-1";

    private String code;
    private String mesg;
    private T data;

    public Result(String code, String mesg) {
        this.code = code;
        this.mesg = mesg;
    }


    public Result(String code, String mesg, T data) {
        this.code = code;
        this.mesg = mesg;
        this.data = data;
    }

    public static Result success(Object data) {
        return new Result(SUCCESSFUL_CODE, SUCCESSFUL_MESG, data);
    }

    public static Result success() {
        return new Result(SUCCESSFUL_CODE, SUCCESSFUL_MESG);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
