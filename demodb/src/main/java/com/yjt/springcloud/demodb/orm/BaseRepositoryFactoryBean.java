package com.yjt.springcloud.demodb.orm;

import com.yjt.springcloud.demodb.entity.BaseEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * TODO
 * ClassName: BaseRepositoryFactoryBean
 * Date: 2019-09-01 22:39
 * author Administrator
 * version V1.0
 */
public class BaseRepositoryFactoryBean <R extends JpaRepository<T, I>, T,I extends Serializable> extends JpaRepositoryFactoryBean<R, T, I> {

    public BaseRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(final EntityManager entityManager) {
        return new JpaRepositoryFactory(entityManager){
            @Override
            protected JpaRepositoryImplementation<T, I> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
                Class<T> domainClass = (Class<T>) information.getDomainType();
                if(BaseEnity.class.isAssignableFrom(domainClass)){
                     return new BaseRepositoryImpl(domainClass,entityManager);
                }else {
                    return new SimpleJpaRepository(domainClass, entityManager);
                }
            }

            @Override
            protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
                return BaseEnity.class.isAssignableFrom(metadata.getDomainType()) ? BaseRepositoryImpl.class : SimpleJpaRepository.class;
            }
        };
    }

}
