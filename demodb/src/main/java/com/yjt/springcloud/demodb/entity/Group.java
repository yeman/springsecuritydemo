package com.yjt.springcloud.demodb.entity;

import com.google.common.collect.Sets;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "t_group")
public class Group extends BaseEnity{

    @Id
    @Column(name = "id")

    private Long groupId;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "parent_group_id")
    private Long parentGroupId;

    @Column(name = "sort_order")
    private String sortOrder;

    @Column(name = "description")
    private String description;

    @Column(name = "enable")
    private String enable;

    @OneToMany(mappedBy = "user")
    private Set<UserGroup> users = Sets.newHashSet();

    @OneToMany(mappedBy = "role")
    private Set<GroupRole> groupRoles = Sets.newHashSet();

}
