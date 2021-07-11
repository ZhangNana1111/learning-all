package com.app.dynamicDataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author ZhangNana
 * @DATE 2021/7/11 9:28
 * @Version 1.0
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DynamicApplication {
    public static void main(String[] args) {
        SpringApplication.run(DynamicApplication.class,args);
    }
}
