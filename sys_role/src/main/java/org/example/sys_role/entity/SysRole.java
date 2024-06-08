package org.example.sys_role.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.common.BaseEntity;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysRole extends BaseEntity {
    private Long roleId;
    private String roleName;
}
