package com.yjt.springcloud.demodb.entity;

import com.yjt.springcloud.demodb.enums.YesNoEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;

public class UserDetailVo implements UserDetails {

    private UserEntity userEntity;

    public UserDetailVo(UserEntity userEntity){
        this.userEntity = userEntity;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userEntity.getPermissionList();
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        LocalDate localDate = LocalDate.now();
        return !localDate.isAfter(userEntity.getAccountExpiredDate());
    }

    @Override
    public boolean isAccountNonLocked() {
        return !YesNoEnum.YES.getKey().equalsIgnoreCase(userEntity.getIsLock());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        LocalDate localDate = LocalDate.now();
        return !localDate.isAfter(userEntity.getPasswdWordExpiredDate());
    }

    @Override
    public boolean isEnabled() {
        return YesNoEnum.YES.getKey().equalsIgnoreCase(userEntity.getEnable());
    }
}
