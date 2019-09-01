package com.yjt.springcloud.demodb.repository;

import com.yjt.springcloud.demodb.entity.Org;
import com.yjt.springcloud.demodb.orm.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 机构
 * ClassName: OrgrRepository
 * Date: 2019-08-31 21:29
 * author Administrator
 * version V1.0
 */
@Repository
public interface OrgRepository extends BaseRepository<Org,Long> {

    List<Org> findByParentId(Long orgId);

}
