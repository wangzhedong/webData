package com.wzd.reportSystem.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
 *  前端控制器
 * </p>
 *
 * @author wzd
 * @since 2019-05-14
 */
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    @PostMapping("save")
    public R save(@RequestBody SysUser sysUser){

        if(sysUser == null){
            return R.failed("数据不能为空！");
        }
        if(StringUtils.isBlank(sysUser.getUserName())){
            return R.failed("用户名不能为空！");
        }
        int i = sysUserService.count(new QueryWrapper<SysUser>().eq("user_name",sysUser.getUserName()).eq("is_delete","0"));
        if(i!= 0 ){
            return R.failed("该用户名已存在！");
        }
        sysUserService.save(sysUser);
        return R.ok("");
    }

    @GetMapping("query")
    public R query(Integer pageIndex,Integer pageSize,String search){
        Page<SysUser> page = new Page<>(pageIndex,pageSize);
        QueryWrapper queryWrapper = null;
        if(StringUtils.isNotBlank(search)){
            queryWrapper = new QueryWrapper<SysUser>().like("user",search).eq("is_delete","0");
        }

        IPage<SysUser> result = sysUserService.page(page,queryWrapper);
        return R.ok(result);
    }


}

