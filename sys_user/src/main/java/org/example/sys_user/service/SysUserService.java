package org.example.sys_user.service;

import org.example.common.CustomException;
import org.example.sys_user.entity.SysUser;

public interface SysUserService {
    void login(String userName, String password);
    void add(SysUser sysUser) throws CustomException;
    void setRole(Long userName, Long roleId, String checkWho);
}
