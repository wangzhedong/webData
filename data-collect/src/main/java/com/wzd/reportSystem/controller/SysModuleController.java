package com.wzd.reportSystem.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.wzd.core.entity.SysModule;
import com.wzd.core.entity.SysUser;
import com.wzd.core.service.SysModuleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  资源模块管理
 * </p>
 *
 * @author wzd
 * @since 2019-06-27
 */
@RestController
@RequestMapping("/sysModule")
public class SysModuleController {

    @Autowired
    private SysModuleService sysModuleService;

    @PostMapping("add")
    public R add(@RequestBody SysModule sysModule){


        return R.ok(null);
    }

    @GetMapping("queryAll")
    public R queryAll(String search){
        LambdaQueryWrapper<SysModule> queryWrapper = null;
        if(StringUtils.isNotBlank(search)){
            queryWrapper = new LambdaQueryWrapper<SysModule>().like(SysModule::getModuleName,search);
        }
        List<SysModule> list = sysModuleService.list(queryWrapper);
        return R.ok(list);
    }

    @PostMapping("update")
    public R update(@RequestBody SysModule sysModule){

        return R.ok(null);
    }

    @GetMapping("del")
    public R del(String id){

        return R.ok(null);
    }
}

