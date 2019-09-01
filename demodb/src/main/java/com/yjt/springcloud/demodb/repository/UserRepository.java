package com.yjt.springcloud.demodb.repository;

import com.yjt.springcloud.demodb.entity.User;
import com.yjt.springcloud.demodb.orm.BaseRepository;
import com.yjt.springcloud.demodb.orm.BaseRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @ClassName UserRepository
 * @Description 用户信息
 * @Author: YM
 * @Version V1.0
 * @Since V1.0
 * @Date: 2019-07-10 17:53
 **/
@Repository
public interface UserRepository extends BaseRepository<User,Long> {

    List<User> findByOrgId(Long orgId);
}
