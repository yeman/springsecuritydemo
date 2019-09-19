package com.yjt.springcloud.demodb.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.yjt.springcloud.demodb.entity.dto.UserListVo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(exclude = {"roleDataPermissions","users","groupRoles"})
@Table(name = "t_role")
public class Role extends BaseEnity{

    @Id
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_code")
    private String roleCode;

    @Column(name = "data_scope")
    private String dataScope;

    @Column(name = "state")
    private String state;

    @Column(name = "sort_order")
    private String sortOrder;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private Set<UserRole> users = Sets.newHashSet();

    @OneToMany(mappedBy = "permission",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private Set<RolePermission>  permissions = Sets.newHashSet();

    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private Set<RoleDataPermission> roleDataPermissions = Sets.newHashSet();

    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private Set<GroupRole> groupRoles = Sets.newHashSet();

    @Transient
    private List<Role> children = Lists.newArrayList();
}
