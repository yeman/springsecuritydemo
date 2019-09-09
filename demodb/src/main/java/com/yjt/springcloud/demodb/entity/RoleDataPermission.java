package com.yjt.springcloud.demodb.entity;


import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "t_role_data_permission")
public class RoleDataPermission extends BaseEnity{

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id",nullable = false)
    private Role role;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "org_id",nullable = false)
    private Org org;
}
