package com.yjt.springcloud.demodb.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class BaseEnity implements Serializable {

    @CreatedBy
    private String creator;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date lastChangeTime;

    @LastModifiedBy
    private String modifier;
}
