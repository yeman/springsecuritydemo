package com.yjt.springcloud.demodb.service.impl;

import com.yjt.springcloud.demodb.entity.UserEntity;
import com.yjt.springcloud.demodb.repository.UserRepository;
import com.yjt.springcloud.demodb.service.UserService;
import lombok.Data;
import lombok.Setter;
import org.hibernate.Criteria;
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
    public void insert(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public UserEntity getByUsername(String username) {
        Example<UserEntity> entityExample = Example.of(UserEntity.builder().build().userName(username));
        Optional<UserEntity> optional = userRepository.findOne(entityExample);
        return optional.isPresent() ? optional.get() : null;
    }
}
