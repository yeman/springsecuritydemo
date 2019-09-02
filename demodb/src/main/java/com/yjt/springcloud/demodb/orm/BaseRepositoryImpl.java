package com.yjt.springcloud.demodb.orm;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * TODO
 * ClassName: BaseRepositorySupport
 * Date: 2019-09-01 22:23
 * author Administrator
 * version V1.0
 */
@Transactional
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private  EntityManager entityManager;

    private Class<T> entityClass;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
        this.entityClass = domainClass;
    }

    public BaseRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public List<T> findListByNativeSql(String nativeSql, Class<T> clz) {
        return entityManager.createNativeQuery(nativeSql, clz).getResultList();
    }

    @Override
    public T update(T t) {
        return entityManager.merge(t);
    }

    @Override
    public int executeUpdate(final String hql, Object... values) {
        Query query = entityManager.createQuery(hql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i + 1, values[i]);
            }
        }
        return query.executeUpdate();
    }

        @Override
        public int executeUpdate (final String hql, Map< String, Object > params){
            Query query = entityManager.createQuery(hql);
            for (String name : params.keySet()) {
                query.setParameter(name, params.get(name));
            }
            return query.executeUpdate();
        }

        @Override
        public int executeUpdate (final String hql, List < Object > values){
            Query query = entityManager.createQuery(hql);
            for (int i = 0; i < values.size(); i++) {
                query.setParameter(i + 1, values.get(i));
            }
            return query.executeUpdate();
        }

        public void batchUpdate (Object...item){
            if (null != item) {
                for (Object entity : item) {
                    entityManager.merge(entity);
                }
            }
        }
    }
