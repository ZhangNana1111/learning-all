package com.app.dynamicDataSource.aop;

import com.app.dynamicDataSource.config.DataSource;
import com.app.dynamicDataSource.config.DynamicDataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 创建一个aop切面，拦截带有@DataSource注解的方法，在方法执行前切换至目标数据源，执行完成后恢复到默认数据源
 * @Author ZhangNana
 * @DATE 2021/7/11 10:13
 * @Version 1.0
 */
@Aspect
@Order(-1)
@Component
public class DynamicDataSourceAspect{

    @Before("@annotation(dataSource)")
    public void switchDataSource(JoinPoint point, DataSource dataSource){
        if (!DynamicDataSourceContextHolder.containDataSourceKey(dataSource.value())){
            System.out.println("DataSource [{}] doesn't exist, use default DataSource [{}] " + dataSource.value());
        }else {
            //切换数据源
            DynamicDataSourceContextHolder.setDataSourceKeys(dataSource.value());
            System.out.println("Switch DataSource to [" + DynamicDataSourceContextHolder.getDataSourceKey()
                    + "] in Method [" + point.getSignature() + "]");
        }
    }


    /**
     * 重置数据源
     * @param point
     * @param dataSource
     */
    @After("@annotation(dataSource))")
    public void restoreDataSource(JoinPoint point, DataSource dataSource) {
        // 将数据源置为默认数据源
        DynamicDataSourceContextHolder.clearDataSourceKey();
        System.out.println("Restore DataSource to [" + DynamicDataSourceContextHolder.getDataSourceKey()
                + "] in Method [" + point.getSignature() + "]");
    }

}
