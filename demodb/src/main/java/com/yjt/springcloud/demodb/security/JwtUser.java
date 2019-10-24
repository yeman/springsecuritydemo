package com.yjt.springcloud.demodb.security;

import com.alibaba.fastjson.annotation.JSONField;
import com.yjt.springcloud.demodb.enums.YesNoEnum;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * TODO
 * ClassName: JwtUser
 * Date: 2019-10-21 21:31
 * author Administrator
 * version V1.0
 */
@Data
public class JwtUser implements UserDetails {

    @JSONField(serialize = false)
    private final Long id;

    private final String username;

    @JSONField(serialize = false)
    private final String password;

    private final String org;

    private final String nickName;

    private final String jobLevel;

    private final String sex;

    private final String avatar;

    private final String email;

    private final String mobile;

    @JSONField(serialize = false)
    private final Collection<GrantedAuthority> authorities;

    private final String enable;

    private final String isLock;

    @JSONField(serialize = false)
    private final LocalDateTime accountExpireDate;

    @JSONField(serialize = false)
    private final LocalDateTime passwordExpireDate;


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountExpireDate.isAfter(LocalDateTime.now());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return passwordExpireDate.isAfter(LocalDateTime.now());
    }

    @Override
    public boolean isAccountNonLocked() {
        return YesNoEnum.NO.getKey().equalsIgnoreCase(isLock);
    }

    @Override
    public boolean isEnabled() {
        return YesNoEnum.YES.getKey().equalsIgnoreCase(enable);
    }

    public Collection getRoles() {
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
    }
}
