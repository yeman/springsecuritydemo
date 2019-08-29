package com.yjt.springcloud.demodb.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
public class UserEntity extends BaseEnity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String userName;

    @JSONField(serialize = false)
    @Column(name = "password")
    private String password;

    @Column(name = "org_id")
    private Long orgId;

    @Column(name = "nick_name")
    private String nickName;

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

    @Column(name = "password_expire_date")
    private LocalDate passwdWordExpiredDate;

    @Column(name = "account_expire_date")
    private LocalDate accountExpiredDate;

    private List<Role> roles = Lists.newArrayList();

    private List<Permission> permissionList = Lists.newArrayList();


}
