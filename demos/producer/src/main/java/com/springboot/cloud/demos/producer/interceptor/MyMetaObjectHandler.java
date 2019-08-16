package com.springboot.cloud.demos.producer.interceptor;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.springboot.cloud.common.web.entity.po.BasePo;
import com.springboot.cloud.common.core.util.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static final String FIELD_CREATED_TIME = "createdTime";
    private static final String FIELD_CREATED_BY = "createdBy";
    private static final String FIELD_UPDATED_TIME = "updatedTime";
    private static final String FIELD_UPDATED_BY = "updatedBy";

    @Override
    public void insertFill(MetaObject metaObject) {
        //避免使用metaObject.setValue()
        String username = getUsername();
        Date now = Date.from(ZonedDateTime.now().toInstant());
        log.debug("start insert fill username:{}", username);
        this.setFieldValByName(FIELD_CREATED_TIME, now, metaObject);
        this.setFieldValByName(FIELD_CREATED_BY, username, metaObject);
        this.setFieldValByName(FIELD_UPDATED_TIME, now, metaObject);
        this.setFieldValByName(FIELD_UPDATED_BY, username, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        String username = getUsername();
        log.debug("start update fill username:{}", username);
        this.setFieldValByName(FIELD_UPDATED_TIME, Date.from(ZonedDateTime.now().toInstant()), metaObject);
        this.setFieldValByName(FIELD_UPDATED_BY, username, metaObject);
    }

    /**
     * 获取当前操作的用户名
     *
     * @return 当前操作用户名，若为空置为system
     */
    private String getUsername() {
        return StringUtils.defaultIfBlank(UserContextHolder.getInstance().getUsername(), BasePo.DEFAULT_USERNAME);
    }
}