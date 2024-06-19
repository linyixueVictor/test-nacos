package org.example.sys_role.service;

import org.example.sys_role.model.SysRole;

public interface SysRoleService {
    SysRole getById(Long roleId);
    void add(SysRole sysRole);
}
