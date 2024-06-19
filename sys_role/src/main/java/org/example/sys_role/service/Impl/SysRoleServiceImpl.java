package org.example.sys_role.service.Impl;

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
        SysRole sysRole = null;
        try {
            sysRole = sysRoleMapper.getById(roleId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(500, Const.ErrorMsg.SysRole.GET, e.getMessage());
        }
        if (sysRole == null) {
            throw new CustomException(500, Const.ErrorMsg.SysRole.NOT_EXIST);
        } else {
            return sysRole;
        }
    }

    @Override
    public void add(SysRole sysRole) {
        SysRole sysRoleOld = sysRoleMapper.getById(sysRole.getRoleId());
        if (sysRoleOld != null) {
            throw new CustomException(500, Const.ErrorMsg.SysRole.EXISTED);
        }
        try {
            sysRoleMapper.add(sysRole);
        } catch (Exception e) {
            throw new CustomException(500, Const.ErrorMsg.SysRole.ADD, e.getMessage());
        }

    }
}
