package com.wzd.core.dto;

import com.wzd.core.entity.SysRole;
import lombok.Data;

import java.util.List;

@Data
public class UserRoleDto {

    private List<String> userIds;

    private List<SysRole> sysRoles;
}
