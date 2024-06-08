package org.example.sys_user.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.common.BaseEntity;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysUser extends BaseEntity {
    private Long userName;
    private String nickName;
    private String password;
    private Character status;
    private String checkWho;
}
