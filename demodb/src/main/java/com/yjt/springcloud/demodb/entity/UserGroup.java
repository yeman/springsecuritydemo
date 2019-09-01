package com.yjt.springcloud.demodb.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "t_user_group")
public class UserGroup extends BaseEnity {

    @Id

    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id",nullable = false)
    private Group group;

}
