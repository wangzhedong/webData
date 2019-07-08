package com.wzd.reportSystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzd.core.entity.ExcelRule;
import com.wzd.core.service.ExcelRuleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  报表规则管理
 * </p>
 *
 * @author wzd
 * @since 2019-05-14
 */
@RestController
@RequestMapping("/rule")
public class ExcelRuleController {

    @Autowired
    private ExcelRuleService excelRuleService;

    /**
     * 新增规则
     * @param rule
     * @return
     */
    @PostMapping("add")
    public R add(@RequestBody ExcelRule rule){
        R r = checkRule(rule,"add");
        if(r != null){
            return r;
        }
        excelRuleService.save(rule);
        return R.ok(null);
    }

    /**
     * 删除规则
     * @param id
     * @return
     */
    @GetMapping("del")
    public R del(String id){
        if(StringUtils.isBlank(id)){
            return R.failed("id为空");
        }
        excelRuleService.removeById(id);
        return R.ok(null);
    }

    /**
     * 更新规则
     * @param rule
     * @return
     */
    @PostMapping("update")
    public R update(@RequestBody ExcelRule rule){
        R r = checkRule(rule,"update");
        if(r != null){
            return r;
        }
        excelRuleService.updateById(rule);
        return R.ok(null);
    }

    /**
     * 分页查询规则数据
     * @param pageIndex
     * @param pageSize
     * @param search
     * @return
     */
    @GetMapping("queryByPage")
    public R queryByPage(Integer pageIndex,Integer pageSize,String search){
        Page<ExcelRule> page = new Page<>(pageIndex,pageSize);
        LambdaQueryWrapper<ExcelRule> queryWrapper = null;
        if(StringUtils.isNotBlank(search)){
            queryWrapper = new LambdaQueryWrapper<ExcelRule>().like(ExcelRule::getRuleName,search);
        }
        IPage<ExcelRule> result = excelRuleService.page(page,queryWrapper);
        return R.ok(result);
    }

    /**
     * 校验规则
     * @param rule
     * @param type
     * @return
     */
    private R checkRule(ExcelRule rule,String type){
        if(rule == null ){
            return R.failed("数据为空!");
        }
        if(StringUtils.isBlank(rule.getRuleName())){
            return R.failed("规则名称为空!");
        }
        if(StringUtils.isBlank(rule.getRuleDetail())){
            return R.failed("规则明细为空!");
        }
        int i = excelRuleService.count(new LambdaQueryWrapper<ExcelRule>().eq(ExcelRule::getRuleName,rule.getRuleName()));
        if(type.equals("add")){
            if(i !=0 ){
                return R.failed("规则名称不能重复");
            }
        }else if(type.equals("update")){
            if(StringUtils.isBlank(rule.getId())){
                return R.failed("id不能为空!");
            }
            if(i > 1){
                return R.failed("规则名称不能重复");
            }
        }
        return null;
    }
}
