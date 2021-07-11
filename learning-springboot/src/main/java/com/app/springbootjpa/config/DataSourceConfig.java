package com.app.springbootjpa.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.app.multiDataSource.config.SecondDataSourceConfig;
import com.github.pagehelper.PageHelper;
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
 * @Author ZhangNana
 * @DATE 2021/7/11 17:02
 * @Version 1.0
 */
@Configuration
//@MapperScan(basePackages = "com.app.springbootjpa")
public class DataSourceConfig {

   // static final String MAPPER_LOCATION = "classpath:mapper/second/*.xml";

    @Value("${spring.datasource.second.url}")
    private String url;

    @Value("${spring.datasource.second.username}")
    private String user;

    @Value("${spring.datasource.second.password}")
    private String password;

    @Value("${spring.datasource.second.driver-class-name}")
    private String driverClass;


    /**
     * 创建数据源bean
     * @return
     */
    @Primary
    @Bean(name = "dataSource")
    //@ConfigurationProperties(prefix = "spring.datasource.second")
    public DataSource secondDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driverClass);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(user);
        druidDataSource.setPassword(password);
        druidDataSource.setDefaultAutoCommit(true);
        return druidDataSource;
    }

    /**
     * 事务管理器
     * @return
     */
   /* @Primary
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager secondTransactionManager(){
        //将数据源注入事务管理器中
        return new DataSourceTransactionManager(secondDataSource());
    }*/

    /**
     * 由于是多多数据源，需要指定具体使用哪个数据源
     * @param secondDataSource
     * @return
     * @throws Exception
     */
    @Primary
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource secondDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(secondDataSource);

       // sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
            //    .getResources(DataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
