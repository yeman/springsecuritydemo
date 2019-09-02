package com.yjt.springcloud.demodb.entity;

import com.google.common.collect.Sets;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Accessors(chain = true)
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

    @Column(name = "parent_role_id")
    private Long parentRoleId;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "user")
    private Set<UserRole> users = Sets.newHashSet();

    @OneToMany(mappedBy = "permission",fetch = FetchType.EAGER)
    private Set<RolePermission>  permissions = Sets.newHashSet();


    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER)
    private Set<RoleDataPermission> roleDataPermissions = Sets.newHashSet();

    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER)
    private Set<GroupRole> groupRoles = Sets.newHashSet();
}
