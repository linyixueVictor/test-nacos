package org.example.sys_role.controller;

import org.example.common.AppHttpCodeEnum;
import org.example.common.Const;
import org.example.common.exception.CustomException;
import org.example.common.R;
import org.example.sys_role.model.SysRole;
import org.example.sys_role.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class SysRoleController {
    @Autowired
    SysRoleService sysRoleService;
    @GetMapping("/getById/{roleId}")
    public R<SysRole> getById(@PathVariable Long roleId) {
        SysRole sysRole = sysRoleService.getById(roleId);
        return R.okResult(AppHttpCodeEnum.SUCCESS_GET, sysRole);
    }

    @PostMapping("/add")
    public R<Long> add(@RequestBody SysRole sysRole) {
        sysRoleService.add(sysRole);
        return R.okResult(AppHttpCodeEnum.SUCCESS);
    }

}
