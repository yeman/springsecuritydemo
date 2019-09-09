package com.yjt.springcloud.demodb.service;

import com.yjt.springcloud.demodb.entity.Org;
import com.yjt.springcloud.demodb.entity.dto.OrgTreeVo;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * 机构
 * ClassName: OrgService
 * Date: 2019-08-31 21:30
 * author Administrator
 * version V1.0
 */
public interface OrgService {

   /*
    * 机构添加
    * @author Administrator
    * @date 2019-08-31 23:29
    * @param org
    * @return com.yjt.springcloud.demodb.entity.Org
    */
    Org insert(@NotNull Org org);

    /*
     * 机构删除
     * @author Administrator
     * @date 2019-08-31 23:30
     * @param org
     * @return void
     */
    void delete(Long org);

    Org updateOrg(Org org);

    OrgTreeVo tree(Map param);

    Org findById(Long orgId);

}
