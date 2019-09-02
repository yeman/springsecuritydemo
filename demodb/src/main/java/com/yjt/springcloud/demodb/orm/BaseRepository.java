package com.yjt.springcloud.demodb.orm;

import com.yjt.springcloud.demodb.entity.BaseEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * TODO
 * ClassName: BaseRepository
 * Date: 2019-09-01 22:22
 * author Administrator
 * version V1.0
 */
@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {

    List<T> findListByNativeSql(String nativeSql, Class<T> clzss);

    void batchUpdate(T ... t);

    T update(T t);

    public int executeUpdate(final String hql, Object... values);

    public int executeUpdate(final String hql, Map<String, Object> params);

    public int executeUpdate(final String hql, List<Object> values);
}
