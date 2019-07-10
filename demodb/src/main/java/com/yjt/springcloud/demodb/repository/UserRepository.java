package com.yjt.springcloud.demodb.repository;

import com.yjt.springcloud.demodb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserRepository
 * @Description TODO
 * @Author: YM
 * @Version V1.0
 * @Since V1.0
 * @Date: 2019-07-10 17:53
 **/
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
}
