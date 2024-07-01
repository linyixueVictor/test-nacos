package org.example.sys_user.controller;

import org.example.common.consts.AppHttpCodeEnum;
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
        Map<String, String> map = sysUserService.login(userName, password);
        return R.okResult(AppHttpCodeEnum.SUCCESS_LOGIN, map);
    }

    @PostMapping("/register")
    public R<Long> add(@RequestBody SysUser sysUser) {
        sysUserService.add(sysUser);
        return R.okResult(AppHttpCodeEnum.SUCCESS_REGISTER);
    }

    @PostMapping("/setRole")
    public R<Long> setRole(@RequestParam String userName, @RequestParam Long roleId,
                     @RequestParam String checkWho) {
        sysUserService.setRole(userName, roleId, checkWho);
        return R.okResult(AppHttpCodeEnum.SUCCESS);
    }
}
