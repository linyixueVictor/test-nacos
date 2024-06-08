package org.example.security.service;

import org.example.security.domain.TestUserDetails;
import org.example.security.entity.SysUser;
import org.example.security.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class TestUserDetailsService implements UserDetailsService {
    @Autowired
    SysUserMapper mapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("用户名不能为空！");
        }
        SysUser sysUser = getUserByName(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名不存在！");
        }
        return new TestUserDetails(sysUser);
    }

    public SysUser getUserByName(String userName) {
        try {
            return mapper.getUserByName(userName);
        } catch (Exception e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
}
