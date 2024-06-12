package org.example.security.service;

import lombok.extern.slf4j.Slf4j;
import org.example.security.entity.SysUser;
import org.example.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TestUserDetailsService implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserMapper mapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = mapper.getUser(username);
        if (user == null) {
            log.warn("用户名不存在");
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<GrantedAuthority> list = new ArrayList<>();
        List<String> authorities = mapper.getAuthority(user.getRoleId());
        if (authorities != null && !authorities.isEmpty()) {
            for (String authority : authorities) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority);
                list.add(grantedAuthority);
            }
        }
        return User.withUsername(username).password(passwordEncoder.encode(user.getPassword()))
                .authorities(list).build();
    }
}
