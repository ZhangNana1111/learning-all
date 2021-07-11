package com.app.springbootjpa.service;

import com.app.springbootjpa.vo.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @Author ZhangNana
 * @DATE 2021/7/11 17:19
 * @Version 1.0
 */
public interface UserInfoService {

    Optional<UserInfo> findById(Integer id);

    List<UserInfo> findAll();

    UserInfo save(UserInfo userInfo);

    void delete(Integer id);

    //分页
    Page<UserInfo> findAll(Pageable pageable);

    List<UserInfo> findByName(String username);

    List<UserInfo> findByNameLike(String username);

    List<UserInfo> findByIdIn(Collection<Integer> id);
}
