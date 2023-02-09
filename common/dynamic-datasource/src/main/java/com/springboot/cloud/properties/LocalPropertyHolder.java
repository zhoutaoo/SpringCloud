package com.springboot.cloud.properties;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author zhaoyong
 * @date 2021年8月30日 10点49分
 */
@Slf4j
public class LocalPropertyHolder implements PropertyHolder  {
    private static final String DB_PROPERTIES = "dynamic-datasource.properties";
    private String propertiesPath;
    private volatile Properties localProperties;

    public LocalPropertyHolder() {
        this(DB_PROPERTIES);
    }

    private volatile static LocalPropertyHolder singleton;
    public static LocalPropertyHolder getSingleton() {
        if (singleton == null) {
            synchronized (LocalPropertyHolder.class) {
                if (singleton == null) {
                    singleton = new LocalPropertyHolder();
                }
            }
        }
        return singleton;
    }

    public LocalPropertyHolder(String propertiesPath) {
        this.propertiesPath = propertiesPath;
    }

    @Override
    public String getValue(String key) {
        Properties roperties = getLocalProperties();
        return (String)roperties.get(key);
    }

    private Properties getLocalProperties(){
        if(localProperties == null){
            synchronized (this){ if(localProperties == null){
                localProperties = new Properties();
                InputStream inputStream = PropertiesManager.class.getClassLoader() .getResourceAsStream(propertiesPath);
                if (inputStream == null) {
                    throw new IllegalStateException("缺少:" + propertiesPath + "配置文件");
                }
                try {
                    localProperties.load(inputStream);
                } catch (IOException e) {
                    log.warn("本地配置加载失败", e);
                }}
            }
        } return localProperties; }

}
