package com.wzd.reportSystem.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzd.core.dto.UserRoleDto;
import com.wzd.core.entity.SysDept;
import com.wzd.core.entity.SysRole;
import com.wzd.core.entity.SysUser;
import com.wzd.core.entity.SysUserRole;
import com.wzd.core.service.SysDeptService;
import com.wzd.core.service.SysUserRoleService;
import com.wzd.core.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  用户管理
 * </p>
 *
 * @author wzd
 * @since 2019-05-14
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    @PostMapping("add")
    public R add(@RequestBody SysUser sysUser){
        R r = checkUser(sysUser,"add");
        if(r != null ){
            return r;
        }
        sysUser.setPassword("123456");
        sysUser.setSex("1");
        sysUserService.save(sysUser);
        return R.ok(null);
    }

    /**
     * 分页查询用户
     * @param pageIndex
     * @param pageSize
     * @param search
     * @return
     */
    @GetMapping("queryByPage")
    public R queryByPage(Integer pageIndex,Integer pageSize,String search){
        Page<SysUser> page = new Page<>(pageIndex,pageSize);
        LambdaQueryWrapper<SysUser> queryWrapper = null;
        if(StringUtils.isNotBlank(search)){
            queryWrapper = new LambdaQueryWrapper<SysUser>().like(SysUser::getUserName,search);
        }

        IPage<SysUser> result = sysUserService.page(page,queryWrapper);
        List<SysUser> list = result.getRecords();
        for(SysUser user:list){
            List<SysUserRole> userRoles = sysUserRoleService.list(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId,user.getId()));
            user.setUserRoles(userRoles);
        }
        List<SysDept> deptList = sysDeptService.list(null);
        return R.ok(result);
    }

    /**
     * 重置密码
     * @param id
     * @return
     */
    @GetMapping("resetPassword")
    public R resetPassword(String id){
        if(StringUtils.isBlank(id)){
            return R.failed("id不能为空！");
        }
        SysUser user = new SysUser();
        user.setId(id);
        user.setPassword("123456");
        sysUserService.updateById(user);
        return R.ok(null);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @GetMapping("del")
    public R del(String id){
        if(StringUtils.isBlank(id)){
            return R.failed("id不能为空！");
        }
        sysUserService.removeById(id);
        return R.ok(null);
    }

    /**
     * 更新用户
     * @param sysUser
     * @return
     */
    @PostMapping("update")
    public R update(@RequestBody SysUser sysUser ){
        R r = checkUser(sysUser,"update");
        if(r != null ){
            return r;
        }
        sysUserService.updateById(sysUser);
        return R.ok(null);
    }

    /**
     * 设置角色
     * @param dto
     * @return
     */
    @PostMapping("setRole")
    public R setRole(@RequestBody UserRoleDto dto){
        List<String> userIds = dto.getUserIds();
        List<SysRole> sysRoles = dto.getSysRoles();
        sysUserRoleService.setRole(userIds,sysRoles);
        return R.ok(null);
    }

    /**
     * 校验用户参数
     * @param sysUser
     * @param type
     * @return
     */
    private R checkUser(SysUser sysUser,String type){
        if(sysUser == null){
            return R.failed("数据不能为空！");
        }
        if(StringUtils.isBlank(sysUser.getUserName())){
            return R.failed("用户名不能为空！");
        }
        if(StringUtils.isBlank((sysUser.getDeptId()))){
            return R.failed("请选择所属部门");
        }
        int i = sysUserService.count(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUserName,sysUser.getUserName()));
        if(type.equals("add")){
            if(i!= 0 ){
                return R.failed("该用户名已存在！");
            }
        }else if(type.equals("update")){
            if(i > 1 ){
                return R.failed("该用户名已存在！");
            }
            if(StringUtils.isBlank(sysUser.getId())){
                return R.failed("id不能为空！");
            }
        }
        return null;
    }
}

