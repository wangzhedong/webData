package com.wzd.reportSystem.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzd.core.entity.SysUser;
import com.wzd.core.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    @PostMapping("add")
    public R add(@RequestBody SysUser sysUser){

        if(sysUser == null){
            return R.failed("数据不能为空！");
        }
        if(StringUtils.isBlank(sysUser.getUserName())){
            return R.failed("用户名不能为空！");
        }
        int i = sysUserService.count(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUserName,sysUser.getUserName()));
        if(i!= 0 ){
            return R.failed("该用户名已存在！");
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
        return R.ok(result);
    }


}

