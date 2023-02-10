package com.springboot.cloud.properties;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 属性管理者:此处也可以改成使用nacos的sdk读取数据源配置,会更加灵活一些
 * @Auther: zhaoyong
 * @Date: 2018/7/11
 * @Description:
 */
@Slf4j
public class PropertiesManager {
	private static List<PropertyHolder> propertyHolderChain = new LinkedList<>();

	static {
		propertyHolderChain.add(new SysPropertyHolder());
		propertyHolderChain.add(new LocalPropertyHolder());
	}

	private PropertiesManager() {

	}

	public static String getString(String key) {
		Optional<String> optional = propertyHolderChain.stream()
				.map(propertyHolder -> propertyHolder.getValue(key))
				.filter(Objects::nonNull)
				.findFirst();
		if(optional.isPresent()) {
			return optional.get();
		}else{
			propertyHolderChain.add(LocalPropertyHolder.getSingleton());
			return propertyHolderChain.stream()
					.map(propertyHolder -> propertyHolder.getValue(key))
					.filter(Objects::nonNull)
					.findFirst().get();
		}
	}

	public static Integer getInteger(String key) {
		try {
			return Integer.parseInt(getString(key));
		} catch (Exception e) {
			log.warn("{}属性获取异常", key, e);
		}
		return null;
	}

	public static Long getLong(String key) {
		try {
			return Long.parseLong(getString(key));
		} catch (Exception e) {
			log.warn("{}属性获取异常", key, e);
		}
		return null;
	}

	public static Float getFloat(String key) {
		try {
			return Float.parseFloat(getString(key));
		} catch (Exception e) {
			log.warn("{}属性获取异常", key, e);
		}
		return null;
	}

	public static boolean getBoolean(String key) {
		return Boolean.parseBoolean(getString(key));
	}

	public static String readDomains(String envName) {
		String domainChars = System.getProperty(envName);
		if (domainChars == null) {
			domainChars = System.getenv(envName);
		}
		return domainChars;
	}

}
