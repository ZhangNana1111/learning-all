package com.app.dynamicDataSource.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Author ZhangNana
 * @DATE 2021/7/11 9:48
 * @Version 1.0
 */
public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>(){
        //将master数据源作为默认的数据源的key
        @Override
        protected String initialValue() {
            return "master";
        }
    };

    // 数据源的key的集合，用于切换时判断数据源是否存在
    public static List<Object> dataSourceKeys = new ArrayList<>();

    /**
     * 切换数据源
     * @param key
     */
    public static void setDataSourceKeys(String key){
        contextHolder.set(key);
    }

    /**
     * 获取数据源
     * @return
     */
    public static String getDataSourceKey(){
        return contextHolder.get();
    }

    /**
     * 重置数据源
     */
    public static void clearDataSourceKey(){
        contextHolder.remove();
    }

    /**
     * 判断是否包含数据源
     * @param key
     * @return
     */
    public static boolean containDataSourceKey(String key){
        return dataSourceKeys.contains(key);
    }

    /**
     * 添加数据源keys
     * @param keys
     * @return
     */
    public static void addDataSourceKeys(Collection<? extends Object> keys){
        for (Object key : keys) {
            dataSourceKeys.add(key);
        }
        //return dataSourceKeys.contains(keys);
    }
}
