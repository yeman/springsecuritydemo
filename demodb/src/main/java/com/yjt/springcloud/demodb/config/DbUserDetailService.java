package com.yjt.springcloud.demodb.config;

import com.yjt.springcloud.demodb.entity.UserDetailVo;
import com.yjt.springcloud.demodb.entity.UserEntity;
import com.yjt.springcloud.demodb.service.PerssionService;
import com.yjt.springcloud.demodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DbUserDetailService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Autowired
    private PerssionService perssionService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = userService.getByUsername(userName);
        if(userEntity==null){
            throw  new UsernameNotFoundException("用户名不存在");
        }else{
            userEntity.setPermissionList()
        }
        return new UserDetailVo(userEntity);
    }
}
