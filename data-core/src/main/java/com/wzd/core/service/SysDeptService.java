package com.wzd.core.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzd.core.entity.ExcelRule;
import com.wzd.core.entity.SysDept;
import com.wzd.core.entity.SysUser;
import com.wzd.core.mapper.ExcelRuleMapper;
import com.wzd.core.mapper.SysDeptMapper;
import com.wzd.core.mapper.SysUserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wzd
 * @since 2019-05-14
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class SysDeptService extends ServiceImpl<SysDeptMapper, SysDept> {


    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private ExcelRuleMapper excelRuleMapper;

    /**
     * 新增部门,以及部门与规则的关系
     * @param dept
     * @param ruleId
     */
    public void addDept(SysDept dept, String ruleId) {
        this.getBaseMapper().insert(dept);
        if (StringUtils.isNotBlank(ruleId)) {
            ExcelRule rule = new ExcelRule();
            rule.setDeptId(dept.getId());
            rule.setId(ruleId);
            excelRuleMapper.updateById(rule);
        }
    }

    /**
     * 更新部门,以及部门与规则的关系
     * 先把规则的dept_id全置为空，如果存在rule_id,再更新deptId
     * @param dept
     * @param ruleId
     */
    public void updateDept(SysDept dept, String ruleId) {
        excelRuleMapper.update(new ExcelRule(),new LambdaUpdateWrapper<ExcelRule>().set(ExcelRule::getDeptId,"").eq(ExcelRule::getDeptId,dept.getId()));
        if (StringUtils.isNotBlank(ruleId)) {
            ExcelRule rule = new ExcelRule();
            rule.setDeptId(dept.getId());
            rule.setId(ruleId);
            excelRuleMapper.updateById(rule);
        }
        this.getBaseMapper().updateById(dept);
    }

    /**
     * 把用户表中部门id更新为空
     * 把规则表中的dept_id更新为空
     * 删除部门
    * @param deptId
     */
    public void delDept(String deptId) {
        sysUserMapper.update(new SysUser(), new LambdaUpdateWrapper<SysUser>().set(SysUser::getDeptId, "").eq(SysUser::getDeptId, deptId));
        excelRuleMapper.update(new ExcelRule(),new LambdaUpdateWrapper<ExcelRule>().set(ExcelRule::getDeptId,"").eq(ExcelRule::getDeptId,deptId));
        this.getBaseMapper().deleteById(deptId);
    }

}
