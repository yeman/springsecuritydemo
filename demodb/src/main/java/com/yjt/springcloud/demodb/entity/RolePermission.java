package com.yjt.springcloud.demodb.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "t_role_permission")
public class RolePermission extends  BaseEnity{

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id",nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "permission_id",nullable = false)
    private Permission permission;


}
