package com.app.multiDataSource.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * second数据源配置类
 * @Author ZhangNana
 * @DATE 2021/7/10 23:42
 * @Version 1.0
 */
@Configuration
@MapperScan(basePackages = SecondDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "secondSqlSessionFactory")
public class SecondDataSourceConfig {

    //second dao层目录
    static final String PACKAGE = "com.app.multiDataSource.dao.second";
    //second 对应的xml文件
    static final String MAPPER_LOCATION = "classpath:mapper/second/*.xml";

    @Value("${spring.datasource.second.url}")
    private String url;

    @Value("${spring.datasource.second.username}")
    private String user;

    @Value("${spring.datasource.second.password}")
    private String password;

    @Value("${spring.datasource.second.driver-class-name}")
    private String driverClass;


    @Primary
    @Bean(name = "secondDataSource")
    //@ConfigurationProperties(prefix = "spring.datasource.second")
    public DataSource secondDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driverClass);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(user);
        druidDataSource.setPassword(password);
        return druidDataSource;
    }

    @Primary
    @Bean(name = "secondTransactionManager")
    public DataSourceTransactionManager secondTransactionManager(){
        return new DataSourceTransactionManager(secondDataSource());
    }

    @Primary
    @Bean(name = "secondSqlSessionFactory")
    public SqlSessionFactory secondSqlSessionFactory(@Qualifier("secondDataSource") DataSource secondDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(secondDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(SecondDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
