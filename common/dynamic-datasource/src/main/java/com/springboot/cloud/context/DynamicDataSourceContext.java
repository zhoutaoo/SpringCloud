package com.springboot.cloud.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhaoyong
 */
public class DynamicDataSourceContext {

    private static final ThreadLocal<String> NAMES = new ThreadLocal<>();

    private static final List<String> BALANCED = new ArrayList<>();

    private static Integer index;

    private static Lock lock = new ReentrantLock();

    /**
     * 获取数据源的名称
     *
     * @return 当前数据源的名称
     */
    public static String current() {
        return NAMES.get();
    }

    /**
     * 切换数据源
     *
     * @param name 数据源的名称
     */
    public static void cut(String name) {
         NAMES.set(name);
    }

    /**
     * 清除当前数据源
     */
    public static void remove() {
        NAMES.remove();
    }

    /**
     * 添加数据源到负载均衡列表
     *
     * @param name 数据源名称
     */
    public static void add(String name) {
        BALANCED.add(name);
        index = 0;
    }

    /**
     * 负载均衡策略：切换到参与负载均衡数据源中的某一个数据源
     */
    private static void cut() {
        if (null == index) {
            throw new NullPointerException("没有设置负载均衡数据源");
        }

        if (lock.tryLock()) {
            try {
                index = index == BALANCED.size() ? 0 : index;
                NAMES.set(BALANCED.get(index));
                index++;
            } finally {
                lock.unlock();
            }
        } else {
            NAMES.set(BALANCED.get(new Random().nextInt(BALANCED.size())));
        }
    }
}
