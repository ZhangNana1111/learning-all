package com.app.dynamicDataSource.config;

import java.lang.annotation.*;

/**
 * @Author ZhangNana
 * @DATE 2021/7/11 10:10
 * @Version 1.0
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    /**
     * 数据源key值
     * @return
     */
    String value();
}
