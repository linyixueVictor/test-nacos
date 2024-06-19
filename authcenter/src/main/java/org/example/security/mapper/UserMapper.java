package org.example.security.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.security.entity.SysUser;

import java.util.List;

@Mapper
@DS("test_sys")
public interface UserMapper {
    @Select("select roleId, password, nickName, status from sys_user where userName = #{userName}")
    SysUser getUser(String userName);
    @Select("select authorityName from sys_authority where roleId = #{roleId}")
    List<String> getAuthority(Long roleId);
}
