package com.springboot.cloud.common.test;

public class PrivateObject {
    private String code;

    public String getCode() {
        return code;
    }

    private String changeCode(String code) {
        this.code = code;
        return this.code;
    }

    private void changeCode() {
        this.code = "aaaaa";
    }
}
