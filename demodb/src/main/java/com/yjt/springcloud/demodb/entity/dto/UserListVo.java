package com.yjt.springcloud.demodb.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * 用户列表
 * ClassName: UserListVo
 * Date: 2019-09-10 21:00
 * author Administrator
 * version V1.0
 */
@Data
public class UserListVo {
    private Long id;
    private String userName;
    private String nickName;
    private String mobile;
    private String enable;
    private String isLock;
    private String creator;
    private Date createTime;


}
