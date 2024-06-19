package org.example.sys_role.controller;

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
        try {
            SysRole sysRole = sysRoleService.getById(roleId);
            return R.ok(Const.SuccessMsg.SysUser.GET, sysRole);
        } catch (CustomException e) {
            e.printStackTrace();
            return R.fail(e.getCode(), e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    @PostMapping("/add")
    public R<Long> add(@RequestBody SysRole sysRole) {
        try {
            sysRoleService.add(sysRole);
            return R.ok(Const.SuccessMsg.SysRole.ADD);
        } catch (CustomException e) {
            e.printStackTrace();
            return R.fail(e.getCode(), e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

}
