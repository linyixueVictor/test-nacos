package org.example.sys_user.controller;

import org.example.common.Const;
import org.example.common.CustomException;
import org.example.common.R;
import org.example.sys_user.entity.SysUser;
import org.example.sys_user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class SysUserController {
    @Autowired
    SysUserService sysUserService;
    @PostMapping("/register")
    public R add(@RequestBody SysUser sysUser) {
        try {
            sysUserService.add(sysUser);
            return R.ok(Const.SuccessMsg.SysUser.ADD);
        } catch (CustomException e) {
            e.printStackTrace();
            return R.fail(e.getCode(), e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    @PostMapping("/setRole")
    public R setRole(@RequestParam Long userName, @RequestParam Long roleId,
                     @RequestParam String checkWho) {
        try {
            sysUserService.setRole(userName, roleId, checkWho);
            return R.ok(Const.SuccessMsg.SysUser.SET);
        } catch (CustomException e) {
            e.printStackTrace();
            return R.fail(e.getCode(), e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
}
