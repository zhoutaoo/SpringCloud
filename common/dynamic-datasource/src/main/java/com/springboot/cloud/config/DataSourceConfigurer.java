package com.springboot.cloud.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.springboot.cloud.context.DynamicDataSourceContext;
import com.springboot.cloud.properties.PropertiesManager;

import javax.sql.DataSource;

;

/**
 * 数据源配置类,在tomcat启动时触发，在该类中生成多个数据源实例并将其注入到 ApplicationContext 中
 * @author zhaoyong
 */
public class DataSourceConfigurer {

    public static DataSource getDataSource(String databaseName){
        DruidDataSource dataSource = new DruidDataSource();
        try {
        String dirverName = PropertiesManager.getString("clean."+databaseName+".driverClassName");
        dataSource.setDriverClassName(dirverName);
        dataSource.setUrl(PropertiesManager.getString("clean."+databaseName+".url"));
        dataSource.setUsername(PropertiesManager.getString("clean."+databaseName+".username"));
        dataSource.setPassword(PropertiesManager.getString("clean."+databaseName+".password"));
        // 最大连接等待
        dataSource.setMaxWait(60000);

        // 连接数配置
        dataSource.setInitialSize(1);
        dataSource.setMinIdle(1);
        dataSource.setMaxActive(50);
        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(2000);
        // 配置一个连接在池中最小生存的时间，单位是毫秒
        dataSource.setMinEvictableIdleTimeMillis(600000);
        dataSource.setMaxEvictableIdleTimeMillis(900000);

        // 检验连接是否有效的查询语句。
        dataSource.setValidationQuery("select 1");
        //  指明是否在归还到池中前进行检验
        dataSource.setTestOnReturn(false);
        dataSource.setTestWhileIdle(true);
        // 指明是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个.
        dataSource.setTestOnBorrow(true);
        dataSource.setDefaultAutoCommit(true);

        // 打开后，增强timeBetweenEvictionRunsMillis的周期性连接检查，minIdle内的空闲连接，每次检查强制验证连接有效性
        dataSource.setKeepAlive(true);
        DynamicDataSourceContext.add(databaseName);
        return dataSource;
        }catch (Exception e){
            return null;
        }
    }


}