package org.example.sys_user.feign;

import org.example.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "sys-role", path = "/role")
public interface SysRoleFeign {
    @GetMapping("/getById/{roleId}")
    R getById(@PathVariable Long roleId);
}
