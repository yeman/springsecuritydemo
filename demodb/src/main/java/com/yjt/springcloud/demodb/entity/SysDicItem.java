package com.yjt.springcloud.demodb.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;


@Entity
@Data
@Accessors(chain = true)
@Table(name = "t_sys_dic_item")
public class SysDicItem extends BaseEnity{

    @Id

    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},optional = false)
    @JoinColumn(name = "dic_id")
    private SysDic sysDic;

    @Column(name = "lable_code")
    private String lableCode;

    @Column(name = "label_name")
    private String lableName;

    @Column(name = "enable")
    private String enable;

    @Column(name = "sort_order")
    private String sortOrder;

    @Column(name = "description")
    private String description;
}
