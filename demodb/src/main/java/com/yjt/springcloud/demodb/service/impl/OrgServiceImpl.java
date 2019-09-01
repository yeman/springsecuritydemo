package com.yjt.springcloud.demodb.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.google.common.collect.Lists;
import com.yjt.springcloud.demodb.entity.Org;
import com.yjt.springcloud.demodb.entity.User;
import com.yjt.springcloud.demodb.exception.BusinessException;
import com.yjt.springcloud.demodb.repository.OrgRepository;
import com.yjt.springcloud.demodb.repository.UserRepository;
import com.yjt.springcloud.demodb.service.OrgService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 机构
 * ClassName: OrgServiceImpl
 * Date: 2019-08-31 21:31
 * author Administrator
 * version V1.0
 */
@Service
@Slf4j
public class OrgServiceImpl implements OrgService {

    @Autowired
    private OrgRepository orgRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Org insert(@NotNull Org org) {
        org.setId(IdWorker.getId());
        List<Org> list = orgRepository.findAll(Example.of(Org.builder().orgCode(org.getOrgCode()).build()));
        if (!list.isEmpty()) {
            throw new BusinessException("机构编码已存在");
        }
        orgRepository.save(org);
        return org;
    }

    @Override
    public void delete(Long orgId) {
        Optional<Org> optional = orgRepository.findById(orgId);
        if (optional.isPresent()) {
            List<Org> subList = orgRepository.findByParentId(orgId);
            List<User> userList = userRepository.findByOrgId(orgId);
            if (!subList.isEmpty()) {
                throw new BusinessException("机构存在下级机构,无法删除");
            }
            if (!userList.isEmpty()) {
                throw new BusinessException("机构存在用户信息,无法删除");
            }
        } else {
            throw new BusinessException("机构信息不存在");
        }
    }

    @Override
    public Org updateOrg(Org org) {
        return orgRepository.up
    }

    @Override
    public Org tree(Map param) {
        if (MapUtils.getLong(param, "id") != null) {
            Optional<Org> current = orgRepository.findById(MapUtils.getLong(param, "id"));
            if(current.isPresent()){
                Org org =  bulidLeaf(current.get(), Lists.newArrayList());
                return org;
            }
        } else {
            // TODO
        }
        return null;
    }

    @Override
    public Org findById(Long orgId) {
        return orgRepository.findById(orgId).get();
    }

    /*
     * 树节点递归查询
     * @author Administrator
     * @date 2019-09-01 21:05
     * @param org
     * @param children
     * @return com.yjt.springcloud.demodb.entity.Org
     */
    protected Org bulidLeaf(Org org, List<Org> children) {
        List<Org> childrens = orgRepository.findByParentId(org.getId());
        if (!childrens.isEmpty()) {
            org.setChildren(childrens);
            childrens.stream().forEach(e -> {
                bulidLeaf(e, orgRepository.findByParentId(e.getId()));
            });
        }
        return org;

    }
}
