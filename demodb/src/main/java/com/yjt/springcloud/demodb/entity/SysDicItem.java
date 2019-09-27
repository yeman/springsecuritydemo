package com.yjt.springcloud.demodb.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "t_sys_dic_item")
public class SysDicItem extends BaseEnity{

    @Id
    private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH},optional = false)
    @JoinColumn(name = "dic_id",nullable = false)
    private SysDic sysDic;

    @Column(name = "label_code")
    private String labelCode;

    @Column(name = "label_name")
    private String labelName;

    @Column(name = "enable")
    private String enable;

    @Column(name = "sort_order")
    private String sortOrder;

    @Column(name = "description")
    private String description;
}
