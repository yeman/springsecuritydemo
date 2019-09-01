package com.yjt.springcloud.demodb.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "t_org")
public class Org extends BaseEnity{

    @Id
    private Long id;

    @Column(name = "org_code",unique = true,nullable = false)
    private String orgCode;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "province_code")
    private String provinceCode;

    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "county_code")
    private String countyCode;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "description")
    private String description;

    @Column(name = "sort_order")
    private String sortOrder;

    @Column(name = "enable")
    private String enable;

    @Column(name = "leader")
    private String leader;

    @Column(name = "leader_mobie")
    private String leaderMobie;

    @Column(name = "leader_email")
    private String leaderEmail;

    @JSONField(serialize = false)
    @OneToMany(mappedBy = "org",fetch = FetchType.EAGER)
    private Set<RoleDataPermission> roleDataPermissions = Sets.newHashSet();

    @Transient
    private List<Org> children = Lists.newArrayList();
}
