package org.example.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.security.entity.SysUser;

@Mapper
public interface SysUserMapper {
    @Select("select * from sys_user where userName = #{userName}")
    SysUser getUserByName(String userName);
}
