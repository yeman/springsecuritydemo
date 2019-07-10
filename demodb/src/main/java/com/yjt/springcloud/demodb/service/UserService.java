package com.yjt.springcloud.demodb.service;

import com.yjt.springcloud.demodb.entity.UserEntity;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author: YM
 * @Version V1.0
 * @Since V1.0
 * @Date: 2019-07-10 18:11
 **/
public interface UserService {
    /**
     * 添加新用户
     *
     * username 唯一， 默认 USER 权限
     */
    void insert(UserEntity userEntity);

    /**
     * 查询用户信息
     * @param username 账号
     * @return UserEntity
     */
    UserEntity getByUsername(String username);
}
