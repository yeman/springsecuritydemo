package com.yjt.springcloud.demodb.entity;

import com.yjt.springcloud.demodb.enums.YesNoEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public class UserDetailVo implements UserDetails {

    private User user;

    public UserDetailVo(User user){
        this.user = user;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return !localDateTime.isAfter(user.getAccountExpiredDate());
    }

    @Override
    public boolean isAccountNonLocked() {
        return !YesNoEnum.YES.getKey().equalsIgnoreCase(user.getIsLock());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return !localDateTime.isAfter(user.getPasswdWordExpiredDate());
    }

    @Override
    public boolean isEnabled() {
        return YesNoEnum.YES.getKey().equalsIgnoreCase(user.getEnable());
    }
}
