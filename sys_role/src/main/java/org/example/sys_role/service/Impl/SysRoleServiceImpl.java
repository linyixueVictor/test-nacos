package org.example.sys_role.service.Impl;

import org.example.common.AppHttpCodeEnum;
import org.example.common.Const;
import org.example.common.exception.CustomException;
import org.example.sys_role.model.SysRole;
import org.example.sys_role.mapper.SysRoleMapper;
import org.example.sys_role.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Override
    public SysRole getById(Long roleId) {
        SysRole sysRole = sysRoleMapper.getById(roleId);
        if (sysRole == null) {
            throw new CustomException(AppHttpCodeEnum.ROLE_NOT_EXISTS);
        } else {
            return sysRole;
        }
    }

    @Override
    public void add(SysRole sysRole) {
        SysRole sysRoleOld = sysRoleMapper.getById(sysRole.getRoleId());
        if (sysRoleOld != null) {
            throw new CustomException(AppHttpCodeEnum.ROLE_EXISTED);
        }
        sysRoleMapper.add(sysRole);
    }
}
