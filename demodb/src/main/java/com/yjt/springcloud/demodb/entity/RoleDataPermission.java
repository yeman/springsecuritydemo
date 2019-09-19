package com.yjt.springcloud.demodb.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "t_role_data_permission")
public class RoleDataPermission extends BaseEnity{

    @Id
    private Long id;

    @JSONField(serialize = false)
    @ManyToOne
    @JoinColumn(name = "role_id",nullable = false)
    private Role role;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "org_id",nullable = false)
    private Org org;
}
