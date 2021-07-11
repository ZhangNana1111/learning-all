package com.app.multiDataSource.dao.second;

import com.app.multiDataSource.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author ZhangNana
 * @DATE 2021/7/11 8:43
 * @Version 1.0
 */
@Mapper
public interface SecondMapper {

    public User getUserInfo(@Param("id") String id);
}
