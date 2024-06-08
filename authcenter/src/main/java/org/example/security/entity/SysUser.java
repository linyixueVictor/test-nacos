package org.example.security.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.common.BaseEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class SysUser extends BaseEntity {
    private Long userId;
    private String userName;
    private String password;
    private Character status;
    private String checkWho;
}
