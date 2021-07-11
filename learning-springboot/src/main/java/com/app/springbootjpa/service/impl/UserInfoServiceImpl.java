package com.app.springbootjpa.service.impl;

import com.app.springbootjpa.repository.UserRepository;
import com.app.springbootjpa.service.UserInfoService;
import com.app.springbootjpa.vo.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @Author ZhangNana
 * @DATE 2021/7/11 17:21
 * @Version 1.0
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserRepository userRepository;

    @Override
    public Optional<UserInfo> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public List<UserInfo> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserInfo save(UserInfo userInfo) {
        return userRepository.save(userInfo);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<UserInfo> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<UserInfo> findByName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<UserInfo> findByNameLike(String username) {
        return userRepository.findByUsernameLike(username);
    }

    @Override
    public List<UserInfo> findByIdIn(Collection<Integer> id) {
        return userRepository.findByIdIn(id);
    }
}
