package com.wzd.reportSystem.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzd.core.entity.SysRole;
import com.wzd.core.service.SysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wzd
 * @since 2019-05-14
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 新增角色
     * @param sysRole
     * @return
     */
    @PostMapping("add")
    public R add(@RequestBody SysRole sysRole){

        if(sysRole == null){
            return R.failed("角色数据不能为空！");
        }
        if(StringUtils.isBlank(sysRole.getRoleName())){
            return R.failed("角色名称不能为空！");
        }
        int i = sysRoleService.count(new LambdaQueryWrapper<SysRole>().eq(SysRole::getRoleName,sysRole.getRoleName()));
        if(i!= 0 ){
            return R.failed("该角色名已存在！");
        }
        sysRoleService.save(sysRole);
        return R.ok(null);
    }

    /**
     * 分页查询角色
     * @param pageIndex
     * @param pageSize
     * @param search
     * @return
     */
    @GetMapping("queryByPage")
    public R queryByPage(Integer pageIndex,Integer pageSize,String search){
        Page<SysRole> page = new Page<>(pageIndex,pageSize);
        LambdaQueryWrapper<SysRole> queryWrapper = null;
        if(StringUtils.isNotBlank(search)){
            queryWrapper = new LambdaQueryWrapper<SysRole>().like(SysRole::getRoleName,search);
        }
        IPage<SysRole> result = sysRoleService.page(page,queryWrapper);
        return R.ok(result);
    }

    /**
     * 查询所有角色
     * @return
     */
    @GetMapping("queryAll")
    public R queryAll(){
        List<SysRole> list = sysRoleService.list(new QueryWrapper<>());
        return R.ok(list);
    }


}

