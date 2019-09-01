package com.yjt.springcloud.demodb.service.impl;

import com.yjt.springcloud.demodb.entity.User;
import com.yjt.springcloud.demodb.repository.UserRepository;
import com.yjt.springcloud.demodb.service.UserService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author: YM
 * @Version V1.0
 * @Since V1.0
 * @Date: 2019-07-10 18:13
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private  UserRepository userRepository;

    @Override
    public void insert(User userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public User getByUsername(String username) {
        User userEntity = new User().setUserName(username);
        Optional<User> optional = userRepository.findOne(Example.of(userEntity));
        return optional.isPresent() ? optional.get() : null;
    }
}
