package com.springboot.oauth2.entity.form;

import org.hibernate.validator.constraints.NotEmpty;

public class UserQueryForm {
    @NotEmpty(message = "姓名不能为空")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
