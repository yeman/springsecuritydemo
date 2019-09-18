package com.yjt.springcloud.demodb.entity.dto;

import lombok.Data;

/**
 * TODO
 * ClassName: UserGroupVo
 * Date: 2019-09-19 1:35
 * author Administrator
 * version V1.0
 */
@Data
public class UserGroupVo {
    private Long id;
    private String groupName;
    private Long userId;
    private String userName;
    private String orgName;
    private String email;
    private String sex;
    private String mobile;

}
