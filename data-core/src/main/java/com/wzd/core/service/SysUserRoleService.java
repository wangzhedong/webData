package com.wzd.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzd.core.entity.SysRole;
import com.wzd.core.entity.SysUserRole;
import com.wzd.core.mapper.SysUserRoleMapper;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzd
 * @since 2019-05-14
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class SysUserRoleService extends ServiceImpl<SysUserRoleMapper, SysUserRole> {


    public void setRole(List<String> userIds,List<SysRole> sysRoles){
        List<SysUserRole> userRoles = new ArrayList<>();
        for(String userId:userIds){
            this.getBaseMapper().physicsDelete(new QueryWrapper<SysUserRole>().eq("user_id",userId));
            for(SysRole role : sysRoles){
                SysUserRole userRole = new SysUserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(role.getId());
                userRole.setRoleName(role.getRoleName());
                userRoles.add(userRole);
            }
        }
        ((SysUserRoleService) AopContext.currentProxy()).saveBatch(userRoles,userRoles.size());
    }

}
