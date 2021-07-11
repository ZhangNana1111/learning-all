package com.app.dynamicDataSource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author ZhangNana
 * @DATE 2021/7/11 9:30
 * @Version 1.0
 */
@Configuration
@MapperScan(basePackages = {"com.app.dynamicDataSource.dao"})
public class DataSourceConfig {

    @Value("${spring.datasource.master.url}")
    private String url;

    @Value("${spring.datasource.master.username}")
    private String user;

    @Value("${spring.datasource.master.password}")
    private String password;

    @Value("${spring.datasource.master.driver-class-name}")
    private String driverClass;

    @Primary
    @Bean("master")
    public DataSource master(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driverClass);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(user);
        druidDataSource.setPassword(password);
        return druidDataSource;
    }

    @Primary
    @Bean("slave")
    @ConfigurationProperties(prefix = "spring.datasource.second")
    public DataSource slave(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource(){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object,Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put("master",master());
        dataSourceMap.put("slave",slave());
        //将master作为默认指定数据源
        dynamicDataSource.setDefaultDataSource(master());
        //将master和slave作为指定数据源
        dynamicDataSource.setDataSource(dataSourceMap);
        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        //配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource作为数据源则不能实现切换
        sessionFactory.setDataSource(dynamicDataSource());
        sessionFactory.setTypeAliasesPackage("com.app.dynamicDataSource.**");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath*:**/dynamic/*.xml"));
        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        // 配置事务管理, 使用事务时在方法头部添加@Transactional注解即可
        return new DataSourceTransactionManager(dynamicDataSource());
    }

}
