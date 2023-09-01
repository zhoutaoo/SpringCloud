package com.springboot.cloud.annotation;

import java.lang.annotation.*;

/**
 * 注解 切换
 * @author zhaoyong
 * @date 2022年6月9日 16点39分
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface SwitchDatabase {
    String value();
}
