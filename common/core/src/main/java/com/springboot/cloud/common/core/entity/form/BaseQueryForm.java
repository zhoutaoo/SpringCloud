package com.springboot.cloud.common.core.entity.form;

import com.springboot.cloud.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

@ApiModel
@Slf4j
public class BaseQueryForm<P extends BaseParam> extends BaseForm {

    /**
     * Form转化为Param
     *
     * @param clazz
     * @return
     */
    public P toParam(Class<P> clazz) {
        P p = null;
        try {
            p = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("Param NewInstance Error");
        }
        BeanUtils.copyProperties(this, p);
        return p;
    }

}
