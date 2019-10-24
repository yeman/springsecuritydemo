package com.yjt.springcloud.demodb.security;

import com.yjt.springcloud.demodb.entity.Org;
import com.yjt.springcloud.demodb.entity.User;
import com.yjt.springcloud.demodb.entity.UserDetailVo;
import com.yjt.springcloud.demodb.service.PerssionService;
import com.yjt.springcloud.demodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtPermissionService perssionService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User userEntity = userService.getByUsername(userName);
        if(userEntity==null){
            throw  new UsernameNotFoundException("用户名不存在");
        }
        return createJwtUser(userEntity);
    }

    private JwtUser createJwtUser(User userEntity) {
        return  new JwtUser(userEntity.getId(),
                userEntity.getUserName(),
                userEntity.getPassword(),
                Optional.ofNullable(userEntity.getOrg()).map(Org::getOrgName).orElse(null),
                userEntity.getNickName(),
                userEntity.getJobLevel(),
                userEntity.getSex(),
                userEntity.getAvatar(),
                userEntity.getEmail(),
                userEntity.getMobile(),
                perssionService.mapToGrantedAuthorities(userEntity),
                userEntity.getEnable(),
                userEntity.getIsLock(),
                userEntity.getAccountExpiredDate(),
                userEntity.getPasswdWordExpiredDate());
    }
}
