package org.example.sys_user.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.sys_user.entity.SysUser;

@Mapper
public interface SysUserMapper {
    void add(SysUser sysUser);
    @Select("select userName from sys_user where userName = #{userName}")
    SysUser selectById(Long userName);
    int setRole(Long userName, Long roleId, String checkWho);
}
