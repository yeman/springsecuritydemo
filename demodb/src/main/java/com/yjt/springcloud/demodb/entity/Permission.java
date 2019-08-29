package com.yjt.springcloud.demodb.entity;

import org.springframework.security.core.GrantedAuthority;

public class Permission implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return null;
    }
}
