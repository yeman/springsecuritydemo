package com.yjt.springcloud.demodb.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

/**
 * @ClassName UserEntity
 * @Description TODO
 * @Author: YM
 * @Version V1.0
 * @Since V1.0
 * @Date: 2019-07-10 17:39
 **/
@Entity
@Data
@Accessors(chain = true)
@Table(name="user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String userName;

    @JSONField(serialize = false)
    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickName;

}
