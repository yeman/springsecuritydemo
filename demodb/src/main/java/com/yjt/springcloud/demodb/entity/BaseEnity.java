package com.yjt.springcloud.demodb.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Accessors(chain = true)
public class BaseEnity implements Serializable {

    @CreatedBy
    @Column(name="creator")
    private String creator;

    @CreatedDate
    @Column(name="create_time")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime = new Date();

    @LastModifiedBy
    @Column(name="last_modifier")
    private String lastModifier;

    @LastModifiedDate
    @Column(name="last_modify_time")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date lastModifyTime = new Date();
}
