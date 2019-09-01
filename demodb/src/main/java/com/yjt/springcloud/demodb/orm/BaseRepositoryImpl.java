package com.yjt.springcloud.demodb.orm;

import com.yjt.springcloud.demodb.entity.BaseEnity;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * TODO
 * ClassName: BaseRepositorySupport
 * Date: 2019-09-01 22:23
 * author Administrator
 * version V1.0
 */
@Transactional
public class BaseRepositoryImpl<T,ID> extends SimpleJpaRepository<T, ID> implements BaseRepository<T,ID>{

    private final EntityManager entityManager;


    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    public BaseRepositoryImpl(JpaEntityInformation<T, Serializable> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    /*@Override
    public List<T> findListByNativeSql(String nativeSql, Class<T> clzss) {
        return entityManager.createNativeQuery(nativeSql, clzss).getResultList();
    }

    @Override
    public T update(T t) {
        return entityManager.merge(t);
    }*/
}
