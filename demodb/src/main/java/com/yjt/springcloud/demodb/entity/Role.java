package com.yjt.springcloud.demodb.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "t_role")
public class Role extends BaseEnity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "state")
    private String roleState;

    @Column(name = "sort_order")
    private String sortOrder;

    @Column(name = "parent_role_id")
    private Long parentRoleId;

    @Column(name = "desc")
    private String description;
}
