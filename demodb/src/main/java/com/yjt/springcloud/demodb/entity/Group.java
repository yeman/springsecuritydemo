package com.yjt.springcloud.demodb.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "t_group")
public class Group extends BaseEnity{

    @Id
    @Column(name = "id")
    private Long groupId;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "parentId")
    private Long parentId;

    @Column(name = "sort_order")
    private String sortOrder;

    @Column(name = "description")
    private String description;

    @Column(name = "enable")
    private String enable;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private Set<UserGroup> users = Sets.newHashSet();

    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER)
    private Set<GroupRole> groupRoles = Sets.newHashSet();

    @Transient
    private List<Group> children = Lists.newArrayList();

}
