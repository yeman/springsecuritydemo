package com.yjt.springcloud.demodb.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.experimental.Accessors;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @ClassName UserEntity
 * @Description 用户实体
 * @Author: YM
 * @Version V1.0
 * @Since V1.0
 * @Date: 2019-07-10 17:39
 **/
@Entity
@Data
@Accessors(chain = true)
@Table(name = "t_user")
public class User extends BaseEnity {

    @Id
    private Long id;

    @Column(name = "username",unique = true,nullable = true)
    private String userName;

    @JSONField(serialize = false)
    @Column(name = "password")
    private String password;

    @Column(name = "org_id")
    private Long orgId;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "job_level")
    private String jobLevel;

    @Column(name = "email")
    private String email;

    @Column(name = "sex")
    private String sex;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "is_lock")
    private String isLock;

    @Column(name = "enable")
    private String enable;

    @Lob
    @Column(name = "avatar",columnDefinition = "TEXT")
    private String avatar;

    @Column(name = "password_expire_date",columnDefinition = "TIMESTAMP")
    private LocalDateTime passwdWordExpiredDate;

    @Column(name = "account_expire_date",columnDefinition = "TIMESTAMP")
    private LocalDateTime accountExpiredDate;

    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER)
    private Set<UserRole> roles = Sets.newHashSet();

    @OneToMany(mappedBy = "group",fetch = FetchType.EAGER)
    private Set<UserGroup> groups = Sets.newHashSet();

    //可选属性optional=false,表示 org 不能为空。删除用户，不影响机构
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="org_id",insertable = false,updatable = false)
    private Org org;

}
