package com.app.dynamicDataSource.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Set;

/**
 * @Author ZhangNana
 * @DATE 2021/7/11 9:38
 * @Version 1.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource {


    /**
     * 如果不希望数据源在启动配置时就加载好，可以定制这个方法，从任何你希望的地方读取并返回数据源
     * 比如从数据库，文件，外部接口等读取数据源信息，并最终返回一个DataSource实现类对象即可
     * @return
     */
    @Override
    protected DataSource determineTargetDataSource() {
        return super.determineTargetDataSource();
    }

    /**
     * 如果希望所有数据源在启动配置时就加载好，这里通过设置数据源key值来进行动态切换
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceKey();
    }

    /**
     * 设置默认数据源
     * @param defaultDataSource
     */
    public void setDefaultDataSource(Object defaultDataSource){
        super.setDefaultTargetDataSource(defaultDataSource);
    }

    public void setDataSource(Map<Object,Object> dataSources){
        super.setTargetDataSources(dataSources);
        //将数据源的key 放在数据源上下文的key集合中，用于切换时判断数据源是否有效
        DynamicDataSourceContextHolder.addDataSourceKeys(dataSources.keySet());
    }






}
