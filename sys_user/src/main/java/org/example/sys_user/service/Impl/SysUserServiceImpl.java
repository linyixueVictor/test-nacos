package org.example.sys_user.service.Impl;

import org.example.common.Const;
import org.example.common.CustomException;
import org.example.common.R;
import org.example.sys_user.dao.SysUserMapper;
import org.example.sys_user.entity.SysUser;
import org.example.sys_user.feign.SysRoleFeign;
import org.example.sys_user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    SysUserMapper mapper;
    @Autowired
    SysRoleFeign sysRoleFeign;

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public void add(SysUser sysUser) {
        SysUser sysUserOld = mapper.selectById(sysUser.getUserName());
        if (sysUserOld != null) {
            throw new CustomException(500, Const.ErrorMsg.SysUser.EXISTED);
        }
        try {
            mapper.add(sysUser);
        } catch (Exception e) {
            throw new CustomException(500, Const.ErrorMsg.SysUser.ADD, e.getMessage());
        }

    }

    @Override
    public void setRole(Long userName, Long roleId, String checkWho) {
        //Ribbon远程调用
        /*String url = "http://sys-role/role/getById/" + roleId;
        R result = restTemplate.getForObject(url, R.class);*/
        //OpenFeign远程调用
        R result = sysRoleFeign.getById(roleId);
        if (!result.get("code").equals(200)) {
            throw new CustomException(500, (String)result.get("msg"));
        }
        if (result.get("code").equals(200)) {
            try {
                int rows = mapper.setRole(userName, roleId, checkWho);
                if (rows == 0) {
                    throw new CustomException(500, Const.ErrorMsg.SysUser.NOT_EXIST);
                }
            } catch (Exception e) {
                throw new CustomException(500, Const.ErrorMsg.SysUser.SET, e.getMessage());
            }
        }
    }
}
