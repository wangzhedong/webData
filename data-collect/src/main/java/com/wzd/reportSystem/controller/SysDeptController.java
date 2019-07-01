package com.wzd.reportSystem.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzd.core.entity.SysDept;
import com.wzd.core.entity.SysUser;
import com.wzd.core.service.SysDeptService;
import com.wzd.core.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  部门管理
 * </p>
 *
 * @author wzd
 * @since 2019-05-14
 */
@RestController
@RequestMapping("/sysDept")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("add")
    public R add(@RequestBody SysDept sysDept){
        if(sysDept == null ){
            return R.failed("数据不能为空");
        }
        if(StringUtils.isBlank(sysDept.getDeptName())){
            return R.failed("部门名称不能为空");
        }
        if(StringUtils.isBlank(sysDept.getDeptNo())){
            return R.failed("部门编号不能为空");
        }
        int i = sysDeptService.count(new LambdaQueryWrapper<SysDept>().eq(SysDept::getDeptName,sysDept.getDeptName()));
        if(i != 0 ){
            return R.failed("部门名称不能重复");
        }
        int j = sysDeptService.count(new LambdaQueryWrapper<SysDept>().eq(SysDept::getDeptNo,sysDept.getDeptNo()));
        if(j != 0 ){
            return R.failed("部门编号不能重复");
        }
        sysDeptService.save(sysDept);
        return R.ok(null);
    }

    @GetMapping("del")
    public R del(String id){
        if(StringUtils.isBlank(id)){
            return R.failed("id不能为空");
        }
        //TODO:这个地方还要删除部门与用户的关系和规则的关系
        sysDeptService.removeById(id);
        return R.ok(null);
    }

    @PostMapping("update")
    public R update(@RequestBody SysDept sysDept){



        return R.ok(null);
    }

    @GetMapping("queryByPage")
    public R queryByPage(Integer pageIndex,Integer pageSize,String search){
        Page<SysDept> page = new Page<>(pageIndex,pageSize);
        LambdaQueryWrapper<SysDept> queryWrapper = null;
        if(StringUtils.isNotBlank(search)){
            queryWrapper = new LambdaQueryWrapper<SysDept>().like(SysDept::getDeptName,search).or().like(SysDept::getDeptNo,search);
        }
        IPage<SysDept> result = sysDeptService.page(page,queryWrapper);
        return R.ok(result);
    }

}

