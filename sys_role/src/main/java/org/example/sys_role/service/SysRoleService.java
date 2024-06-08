package org.example.sys_role.service;

import org.example.sys_role.entity.SysRole;

public interface SysRoleService {
    SysRole getById(Long roleId);
    void add(SysRole sysRole);
}
