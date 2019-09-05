package com.springboot.cloud.common.web.entity.param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.cloud.common.web.entity.po.BasePo;
import lombok.Data;

import java.util.Date;

/**
 * Created by zhoutaoo on 2018/6/1.
 */
@Data
public class BaseParam<T extends BasePo> {
    private Date createdTimeStart;
    private Date createdTimeEnd;

    public QueryWrapper<T> build() {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge(null != this.createdTimeStart, "created_time", this.createdTimeStart)
                .le(null != this.createdTimeEnd, "created_time", this.createdTimeEnd);
        return queryWrapper;
    }
}
