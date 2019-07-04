package com.wzd.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzd.core.entity.SysDept;
import com.wzd.core.entity.SysDeptRule;
import com.wzd.core.entity.SysUser;
import com.wzd.core.mapper.SysDeptMapper;
import com.wzd.core.mapper.SysDeptRuleMapper;
import com.wzd.core.mapper.SysUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
    private SysDeptRuleMapper sysDeptRuleMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 新增部门,以及部门与规则的关系
     *
     * @param dept
     * @param ruleIds
     */
    public void addDept(SysDept dept, List<String> ruleIds) {
        this.getBaseMapper().insert(dept);
        if (ruleIds != null && !ruleIds.isEmpty()) {
            addDeptRule(ruleIds, dept.getId());
        }
    }

    /**
     * 更新部门,以及部门与规则的关系
     * 先删除关系表数据,再重新插入
     *
     * @param dept
     * @param ruleIds
     */
    public void updateDept(SysDept dept, List<String> ruleIds) {
        if (ruleIds != null && !ruleIds.isEmpty()) {
            //先删除部门规则关系表
            sysDeptRuleMapper.physicsDelete((new QueryWrapper<SysDeptRule>().eq("dept_id", dept.getId())));
            addDeptRule(ruleIds, dept.getId());
        }
        this.getBaseMapper().updateById(dept);
    }

    /**
     * 删除部门
     * 把用户表中部门id更新为空
     * 删除部门规则关系表
     * 删除部门
    * @param deptId
     */
    public void delDept(String deptId) {
        sysUserMapper.update(new SysUser(), new LambdaUpdateWrapper<SysUser>().set(SysUser::getDeptId, "").eq(SysUser::getDeptId, deptId));
        sysDeptRuleMapper.physicsDelete((new QueryWrapper<SysDeptRule>().eq("dept_id", deptId)));
        this.getBaseMapper().deleteById(deptId);
    }

    /**
     * 新增部门与规则的关系
     *
     * @param ruleIds
     * @param deptId
     */
    private void addDeptRule(List<String> ruleIds, String deptId) {
        for (String ruleId : ruleIds) {
            SysDeptRule deptRule = new SysDeptRule();
            deptRule.setDeptId(deptId);
            deptRule.setRuleId(ruleId);
            sysDeptRuleMapper.insert(deptRule);
        }
    }
}
