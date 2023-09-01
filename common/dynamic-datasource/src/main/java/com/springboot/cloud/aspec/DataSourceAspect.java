package com.springboot.cloud.aspec;

import com.springboot.cloud.annotation.SwitchDatabase;
import com.springboot.cloud.context.DynamicDataSourceContext;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @author zhaoy
 */
@Order(0)
@Aspect
@Component
public class DataSourceAspect {

    @Pointcut("@annotation(com.springboot.cloud.annotation.SwitchDatabase)")
    public void point() {

    }

    @Before("point()&&@annotation(source)")
    public void before(SwitchDatabase source) {
        DynamicDataSourceContext.cut(source.value());
    }

    @After("point()")
    public void after() {
        DynamicDataSourceContext.remove();
    }
}
