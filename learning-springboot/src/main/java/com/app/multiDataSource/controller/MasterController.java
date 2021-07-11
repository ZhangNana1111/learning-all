package com.app.multiDataSource.controller;

import com.app.multiDataSource.dao.master.MasterMapper;
import com.app.multiDataSource.dao.second.SecondMapper;
import com.app.multiDataSource.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ZhangNana
 * @DATE 2021/7/11 8:41
 * @Version 1.0
 */
@RestController
public class MasterController {

    @Autowired
    MasterMapper masterMapper;

    @Autowired
    SecondMapper secondMapper;

    @RequestMapping("/user")
    public User getUserInfo(){
        User user = masterMapper.getUser("1");
        return user;
    }

    @RequestMapping("/test")
    public User getTestUserInfo(){
        User user = secondMapper.getUserInfo("1");
        return user;
    }

}
