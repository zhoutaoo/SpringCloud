package com.springboot.cloud.config;

import com.springboot.cloud.context.DynamicDataSource;
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
    private static final String DB = "xxl_job,court,big_data_qcc_data,eci_data,elasticsearch_sync,elasticsearch_sync2,company_risk,company_risk_detail,comp_data,company_risk_spider,idc_company_other,copyright,comp_other,big_data_os_invest,eci_radar,eci_market,tidb_company_risk,base_clean_data,person_data,financial_db,business_result_data,qcc_data,elasticsearch_sync3,manage_enterprise_db,manage_globalsys_db,manage_ipright_db,manage_lawsuit_db,manage_news_db,manage_risk_db,manage_person_db,base_enterprise_db,base_financial_db,base_globalsys_db,base_ipright_db,base_news_db,base_risk_db,base_person_db,prod_enterprise_db,prod_financial_db,prod_globalsys_db,prod_ipright_db,prod_news_db,prod_risk_db,prod_person_db,eci_data_read,idc_company_news,content_data,idc_comapny_other,search_sync_news,qcc_product,base_clean_data_tmp,search_sync_risk,search_sync_enterprise,search_sync_risk_write,dap_system,company_extend_os";
    /**
     * spring boot 启动后将自定义创建好的数据源对象放到TargetDataSources中用于后续的切换数据源用
     * 同时指定默认数据源连接
     * @return 动态数据源对象
     */
    @Bean
    public DynamicDataSource dynamicDataSource() {
        DynamicDataSource sources = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        List<String> dbList = Arrays.asList(DB.split(",").clone());
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
