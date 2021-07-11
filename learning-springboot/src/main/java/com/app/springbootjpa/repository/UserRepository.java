package com.app.springbootjpa.repository;

import com.app.springbootjpa.vo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @Author ZhangNana
 * @DATE 2021/7/11 17:13
 * @Version 1.0
 */
public interface UserRepository extends JpaRepository<UserInfo,Integer> {

    //自定义方法
    List<UserInfo> findByUsername(String username);

    List<UserInfo> findByUsernameLike(String username);

    List<UserInfo> findByIdIn(Collection<Integer> id);
}
