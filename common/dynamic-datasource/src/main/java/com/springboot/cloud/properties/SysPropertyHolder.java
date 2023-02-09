package com.springboot.cloud.properties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class SysPropertyHolder implements PropertyHolder {
    @Override
    public String getValue(String key) {
        String property = System.getenv(key);
        if (property != null) {
            return property;
        }
        property = System.getProperty(key);
        if (property != null) {
            return property;
        }
        return null;
    }
}
