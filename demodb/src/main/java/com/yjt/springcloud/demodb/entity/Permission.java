package com.yjt.springcloud.demodb.entity;

import com.google.common.collect.Sets;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;


@Entity
@Data
@Accessors(chain = true)
@Table(name = "t_permission")
public class Permission extends BaseEnity implements GrantedAuthority {

    @Id

    private Long id;

    @Column(name = "permission_type")
    private String permissionType;

    @Column(name = "open_type")
    private String openType;

    @Column(name = "icon")
    private String icon;

    @Column(name = "permission_expression")
    private String permissionExpression;

    @Column(name = "permission_name")
    private String permissionName;

    @Column(name = "permission_code")
    private String permissionCode;

    @Column(name = "description")
    private String description;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "sort_order")
    private String sortOrder;


    @Column(name = "enable")
    private String enable;

    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER)
    private Set<RolePermission> roles = Sets.newHashSet();

    @OneToMany(mappedBy = "group",fetch = FetchType.EAGER)
    private Set<GroupPermission> groups = Sets.newHashSet();

    @Override
    public String getAuthority() {
        return null;
    }
}
