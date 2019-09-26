package com.yjt.springcloud.demodb.entity;


import com.google.common.collect.Sets;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "t_sys_dic")
public class SysDic extends BaseEnity{

    @Id
    private Long id;

    @Column(name = "dic_type",unique = true)
    private String dicType;

    @Column(name = "dic_type_name")
    private String dicTypeName;

    @Column(name = "enable")
    private String enable;

    @Column(name = "sort_order")
    private String sortOrder;

    @Column(name = "description")
    private String description;


    @OneToMany(mappedBy = "sysDic",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private Set<SysDicItem> items = Sets.newHashSet();

}
