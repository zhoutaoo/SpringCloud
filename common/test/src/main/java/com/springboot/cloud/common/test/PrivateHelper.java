package com.springboot.cloud.common.test;

import java.lang.reflect.Field;

/**
 * Created by zhoutaoo on 2018/7/1.
 */
public class PrivateHelper {

    public void setPrivateField(Object instance, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field signingKeyField = instance.getClass().getDeclaredField(fieldName);
        signingKeyField.setAccessible(true);
        signingKeyField.set(instance, value);
    }

}
