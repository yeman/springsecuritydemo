package com.yjt.springcloud.demodb.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import java.io.Serializable;
import java.util.Date;

@Data
@EntityListeners(AuditingEntityListener.class)
@Accessors(chain = true)
public class BaseEnity implements Serializable {

    @CreatedBy
    @Column(name="creator")
    private String creator;

    @CreatedDate
    @Column(name="create_time")
    private Date createTime;

    @LastModifiedBy
    @Column(name="last_modifier")
    private String lastModifier;

    @LastModifiedDate
    @Column(name="last_modify_time")
    private Date lastModifyTime;
}
