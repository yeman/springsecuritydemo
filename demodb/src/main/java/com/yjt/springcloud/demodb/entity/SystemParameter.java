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
@Table(name = "t_sys_parameter")
public class SystemParameter extends BaseEnity{

    @Id
    private Long id;

    @Column(name = "parameter_name")
    private String parameterName;

    @Column(name = "parameter_key")
    private String parameterKey;

    @Column(name = "parameter_value")
    private String parameterValue;

    @Column(name = "system_default")
    private String systemDefault;

    @Column(name = "enable")
    private String enable;

    @Column(name = "sort_order")
    private String sortOrder;

    @Column(name = "description")
    private String description;

}
