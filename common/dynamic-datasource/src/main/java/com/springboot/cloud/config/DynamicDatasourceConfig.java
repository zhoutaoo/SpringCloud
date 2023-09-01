package com.springboot.cloud.config;

import com.springboot.cloud.context.DynamicDataSource;
import com.springboot.cloud.properties.PropertiesManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author zhaoyong
 */
@Configuration
public class DynamicDatasourceConfig {
    public static final String CLEAN_DB_CONNECTIONS = "clean.db.connections";
    /**
     * spring boot 启动后将自定义创建好的数据源对象放到TargetDataSources中用于后续的切换数据源用
     * 同时指定默认数据源连接
     * @return 动态数据源对象
     */
    @Bean
    public DynamicDataSource dynamicDataSource() {
        DynamicDataSource sources = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        String cleanDbConnections = PropertiesManager.getString(CLEAN_DB_CONNECTIONS);
        List<String> dbList = Arrays.asList(cleanDbConnections.split(",").clone());
        dbList.stream().forEach(db->{
            DataSource dataSource = DataSourceConfigurer.getDataSource(db);
            if(dataSource != null) {
                targetDataSources.put(db, dataSource);
            }
        });
        sources.setTargetDataSources(targetDataSources);
        sources.afterPropertiesSet();
        return sources;
    }

    /**
     * 将动态数据源添加到事务管理器中，并生成新的bean
     * @return the platform transaction manager
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
