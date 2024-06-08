package org.example.sys_role.controller;

import org.example.common.Const;
import org.example.common.CustomException;
import org.example.common.R;
import org.example.sys_role.entity.SysRole;
import org.example.sys_role.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class SysRoleController {
    @Autowired
    SysRoleService sysRoleService;
    @GetMapping("/getById/{roleId}")
    public R getById(@PathVariable Long roleId) {
        try {
            SysRole sysRole = sysRoleService.getById(roleId);
            return R.ok(Const.SuccessMsg.SysUser.GET).put("data",sysRole);
        } catch (CustomException e) {
            e.printStackTrace();
            return R.fail(e.getCode(), e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    @PostMapping("/add")
    public R add(@RequestBody SysRole sysRole) {
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
