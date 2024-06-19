package org.example.sys_user.service;

import org.example.common.exception.CustomException;
import org.example.sys_user.model.SysUser;

import java.util.Map;

public interface SysUserService {
    Map<String, String> login(String userName, String password);
    void add(SysUser sysUser) throws CustomException;
    void setRole(String userName, Long roleId, String checkWho);
}
