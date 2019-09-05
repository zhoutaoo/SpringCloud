package com.springboot.cloud.common.web.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.springboot.cloud.common.core.util.UserContextHolder;
import com.springboot.cloud.common.web.entity.po.BasePo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.reflection.MetaObject;

import java.time.ZonedDateTime;
import java.util.Date;

@Slf4j
public class PoMetaObjectHandler implements MetaObjectHandler {
    /**
     * 获取当前交易的用户，为空返回默认system
     *
     * @return
     */
    private String getCurrentUsername() {
        return StringUtils.defaultIfBlank(UserContextHolder.getInstance().getUsername(), BasePo.DEFAULT_USERNAME);
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setInsertFieldValByName("createdBy", getCurrentUsername(), metaObject);
        this.setInsertFieldValByName("createdTime", Date.from(ZonedDateTime.now().toInstant()), metaObject);
        this.updateFill(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setUpdateFieldValByName("updatedBy", getCurrentUsername(), metaObject);
        this.setUpdateFieldValByName("updatedTime", Date.from(ZonedDateTime.now().toInstant()), metaObject);
    }
}