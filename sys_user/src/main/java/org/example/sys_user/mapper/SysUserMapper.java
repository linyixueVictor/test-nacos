package org.example.sys_user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.sys_user.model.SysUser;

@Mapper
public interface SysUserMapper {
    void add(SysUser sysUser);
    @Select("select userName, nickName from sys_user where userName = #{userName}")
    SysUser getById(String userName);
    int setRole(String userName, Long roleId, String checkWho);
}
