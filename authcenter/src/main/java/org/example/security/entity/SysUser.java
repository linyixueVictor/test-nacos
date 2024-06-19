package org.example.security.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.common.BaseEntity;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysUser extends BaseEntity {
    private String userName;
    private String nickName;
    private String password;
    private Character status;
    private String checkWho;
    private Long roleId;
}
