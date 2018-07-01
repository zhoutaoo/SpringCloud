package com.springboot.cloud.common.test;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

public class PrivateHelperTest {

    private PrivateHelper instance = PrivateHelper.getInstance();

    @Test
    public void testSetPrivateField_假如对象有一个私有成员变量_当通过该方法给私有成员变量赋值_那么可以赋值成功() throws Exception {
        PrivateObject instance = new PrivateObject();
        PrivateHelper.getInstance().setPrivateField(instance, "code", "123456");
        Assert.assertEquals("123456", instance.getCode());
    }

    @Test
    public void testInvokeMethod_假如对象有一个有参私有方法_当通过该方法调用私有方法_那么可以调用成功并返回结果() {
        PrivateObject instance = new PrivateObject();
        Method method = this.instance.findMethod(instance, "changeCode", String.class);
        String code = (String) this.instance.invokePrivateMethod(instance, method, "abcef");
        Assert.assertEquals("abcef", code);
    }

    @Test
    public void testInvokeMethod_假如对象有一个无参私有方法_当通过该方法调用私有方法_那么可以调用成功() {
        PrivateObject instance = new PrivateObject();
        Method method = this.instance.findMethod(instance, "changeCode");
        this.instance.invokePrivateMethod(instance, method);
        Assert.assertEquals("aaaaa", instance.getCode());
    }

}