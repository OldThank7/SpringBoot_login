package com.oldthank.secutity;

import com.oldthank.entity.Role;
import com.oldthank.entity.User;
import com.oldthank.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDetail implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.getUserById(s);
        if(user != null){
            List<Role> roles = userMapper.getByUid(user.getId());
            user.setRoles(roles);
            return user;
        }
        throw new UsernameNotFoundException("用户名未找到");
    }
}