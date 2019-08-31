package com.springboot.cloud.common.web.interceptor;

import com.springboot.cloud.common.web.entity.po.BasePo;
import com.springboot.cloud.common.core.util.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Properties;

@Component
@Intercepts({
        @Signature(type = ParameterHandler.class, method = "setParameters", args = {PreparedStatement.class})
})
@Slf4j
public class AuditInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        DefaultParameterHandler statementHandler = (DefaultParameterHandler) invocation.getTarget();
        if (statementHandler.getParameterObject() instanceof BasePo) {
            BasePo parameter = (BasePo) statementHandler.getParameterObject();
            String username = StringUtils.defaultIfBlank(UserContextHolder.getInstance().getUsername(), BasePo.DEFAULT_USERNAME);
            log.debug("mybatis intercept fill username:{}", username);
            parameter.setCreatedBy(username);
            parameter.setCreatedTime(new Date());
            parameter.setUpdatedBy(username);
            parameter.setUpdatedTime(new Date());
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

}
