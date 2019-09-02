package com.yjt.springcloud.demodb.entity;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "t_group_role")
public class GroupRole extends BaseEnity {

    @Id
    private Long id;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name = "group_id",nullable = false)
    private Group group;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name = "role_id")
    private Role role;

}
