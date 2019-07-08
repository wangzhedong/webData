package com.wzd.reportSystem.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzd.core.dto.DeptDto;
import com.wzd.core.entity.ExcelRule;
import com.wzd.core.entity.SysDept;
import com.wzd.core.service.ExcelRuleService;
import com.wzd.core.service.SysDeptService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private ExcelRuleService excelRuleService;


    @PostMapping("add")
    public R add(@RequestBody DeptDto dto){
        R r = checkDept(dto,"add");
        if(r != null){
            return r;
        }
        SysDept dept = new SysDept();
        BeanUtils.copyProperties(dto,dept);
        sysDeptService.addDept(dept,dto.getRuleId());
        return R.ok(null);
    }

    @GetMapping("del")
    public R del(String id){
        if(StringUtils.isBlank(id)){
            return R.failed("id不能为空");
        }
        sysDeptService.delDept(id);
        return R.ok(null);
    }

    @PostMapping("update")
    public R update(@RequestBody DeptDto dto){
        R r = checkDept(dto,"update");
        if(r != null){
            return r;
        }
        SysDept dept = new SysDept();
        BeanUtils.copyProperties(dto,dept);
        sysDeptService.updateDept(dept,dto.getRuleId());
        return R.ok(null);
    }

    /**
     * 分页查询部门信息,查询已选的规则信息
     * 查询所有的规则信息
     * @param pageIndex
     * @param pageSize
     * @param search
     * @return
     */
    @GetMapping("queryByPage")
    public R queryByPage(Integer pageIndex,Integer pageSize,String search){
        String[] selectFields = {"id","rule_name","dept_id"};
        //未被选过的规则
        List<ExcelRule> rules = new ArrayList<>();
        //已经选择过的规则
        List<ExcelRule> checkedRules = new ArrayList<>();
        List<ExcelRule> allRules = excelRuleService.list(new QueryWrapper<ExcelRule>().select(selectFields));
        for(ExcelRule rule: allRules){
            if(StringUtils.isNotBlank(rule.getDeptId())){
                checkedRules.add(rule);
            }else{
                rules.add(rule);
            }
        }
        Map<String,String> map = checkedRules.stream().collect(Collectors.toMap(ExcelRule::getDeptId,ExcelRule::getRuleName));
        Map<String,Object> result = new HashMap<>();
        //rules.stream().filter(r-> StringUtils.isBlank(r.getDeptId())).collect(Collectors.toList());
        Page<SysDept> page = new Page<>(pageIndex,pageSize);
        LambdaQueryWrapper<SysDept> queryWrapper = null;
        if(StringUtils.isNotBlank(search)){
            queryWrapper = new LambdaQueryWrapper<SysDept>().like(SysDept::getDeptName,search).or().like(SysDept::getDeptNo,search);
        }
        IPage<SysDept> pageResult = sysDeptService.page(page,queryWrapper);
        for(SysDept dept : pageResult.getRecords()){
            if(StringUtils.isNotBlank(map.get(dept.getId()))){
                ExcelRule excelRule = new ExcelRule();
                excelRule.setDeptId(dept.getId());
                excelRule.setRuleName(map.get(dept.getId()));
                dept.setExcelRule(excelRule);
            }
        }
        result.put("pages",pageResult);
        result.put("rules",rules);
        return R.ok(result);
    }

    /**
     * 入参数据校验
     * @param dto
     * @param type
     * @return
     */
    private R checkDept(DeptDto dto,String type){
        if(dto == null ){
            return R.failed("数据不能为空");
        }
        if(StringUtils.isBlank(dto.getDeptName())){
            return R.failed("部门名称不能为空");
        }
        if(StringUtils.isBlank(dto.getDeptNo())){
            return R.failed("部门编号不能为空");
        }

        if(type.equals("add")){
            int i = sysDeptService.count(new LambdaQueryWrapper<SysDept>().eq(SysDept::getDeptName,dto.getDeptName()));
            if(i != 0 ){
                return R.failed("部门名称已存在");
            }
            int j = sysDeptService.count(new LambdaQueryWrapper<SysDept>().eq(SysDept::getDeptNo,dto.getDeptNo()));
            if(j != 0 ){
                return R.failed("部门编号已存在");
            }
        }else if(type.equals("update")){
            if(StringUtils.isBlank(dto.getId())){
                return R.failed("id不能为空！");
            }
            List<SysDept> li = sysDeptService.list(new LambdaQueryWrapper<SysDept>().eq(SysDept::getDeptName,dto.getDeptName()));
            if(li != null && li.size() == 1 && !(li.get(0).getId().equals(dto.getId())) ){
                return R.failed("部门名称已存在");
            }
            List<SysDept> lj = sysDeptService.list(new LambdaQueryWrapper<SysDept>().eq(SysDept::getDeptNo,dto.getDeptNo()));
            if(lj != null && lj.size() == 1 && !(lj.get(0).getId().equals(dto.getId()))){
                return R.failed("部门编号已存在");
            }
        }
        return null;
    }

}

