package com.app.dynamicDataSource.controller;

import com.app.dynamicDataSource.config.DataSource;
import com.app.dynamicDataSource.dao.DynamicMapper;
import com.app.dynamicDataSource.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ZhangNana
 * @DATE 2021/7/11 8:41
 * @Version 1.0
 */
@RestController
public class DynamicController {

    @Autowired
    DynamicMapper dynamicMapper;



    @DataSource("master")
    @RequestMapping("/test")
    public User getTestUserInfo(){
        User user = dynamicMapper.getUserInfo("1");
        return user;
    }

    @DataSource("slave")
    @RequestMapping("/test1")
    public User getTestUserInfo1(){
        User user = dynamicMapper.getTestInfo("1");
        return user;
    }



}
