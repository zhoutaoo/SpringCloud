package com.springboot.producer.entity.form;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserQueryForm {
    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date updatedDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
