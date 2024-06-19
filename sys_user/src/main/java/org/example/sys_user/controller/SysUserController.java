package org.example.sys_user.controller;

import org.example.common.Const;
import org.example.common.exception.CustomException;
import org.example.common.R;
import org.example.sys_user.model.SysUser;
import org.example.sys_user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class SysUserController {
    @Autowired
    SysUserService sysUserService;
    @PostMapping("/login")
    public R<Map<String, String>> login(@RequestParam String userName, @RequestParam String password) {
        try {
            Map<String, String> map = sysUserService.login(userName, password);
            return R.ok(Const.SuccessMsg.SysUser.LOGIN, map);
        } catch (CustomException e) {
            e.printStackTrace();
            return R.fail(e.getCode(), e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    @PostMapping("/register")
    public R<Long> add(@RequestBody SysUser sysUser) {
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
    public R<Long> setRole(@RequestParam String userName, @RequestParam Long roleId,
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
