package org.example.sys_role.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.common.Const;
import org.example.sys_role.entity.SysRole;

@Mapper
public interface SysRoleMapper {
    @Select("select * from sys_role where roleId = #{roleId}")
    SysRole getById(Long roleId);
    int add(SysRole sysRole);
}
