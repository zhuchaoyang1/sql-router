package com.example.sqlrouter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zcy
 * @date 2022/8/13
 * @description TODO
 */
@Configuration
public class SlaveDataSourceConfig {

    /**
     * 构造主数据源
     *
     * @return
     */
    @ConfigurationProperties("spring.master.datasource.hikari")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 构造从数据源
     *
     * @return
     */
    @ConfigurationProperties("spring.slave1.datasource.hikari")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 构造动态数据源并交付给SpringBoot的MybatisAutoConfiguration去构造SqlSessionFactory，而构造SqlSessionFactory时，则需要传递一个DataSource接口实例
     *
     * @return
     */
    @Primary
    @Bean("dynamicDataSource")
    public DynamicDataSource dynamicDataSource() {
        // 内部需要维护所有的数据源对应的Map
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("master", masterDataSource());
        dataSourceMap.put("slave1", slaveDataSource());

        /**
         * 设置动态数据源默认的连接和所有的连接
         */
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource());

        return dynamicDataSource;
    }

}
