package com.oldthank.secutity.Provider;

import com.oldthank.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MyAuthencationProvider implements AuthenticationProvider {

    @Autowired
    private com.oldthank.secutity.UserDetail UserDetail;

    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        User jwtUser = (User) UserDetail.loadUserByUsername(username);
        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
        return new UsernamePasswordAuthenticationToken(jwtUser, jwtUser.getPassword(), authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}