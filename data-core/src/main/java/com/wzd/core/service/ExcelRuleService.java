package com.wzd.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzd.core.entity.ExcelRule;
import com.wzd.core.entity.SysDeptRule;
import com.wzd.core.mapper.ExcelRuleMapper;
import com.wzd.core.mapper.SysDeptRuleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 *  报表匹配规则Service
 * </p>
 *
 * @author wzd
 * @since 2019-05-14
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class ExcelRuleService extends ServiceImpl<ExcelRuleMapper, ExcelRule> {

    @Resource
    private SysDeptRuleMapper sysDeptRuleMapper;

    /**
     * 删除规则，以及规则与部门的关系数据
     * @param ruleId
     */
    public void delRle(String ruleId){
        sysDeptRuleMapper.physicsDelete((new QueryWrapper<SysDeptRule>().eq("rule_id", ruleId)));
        this.getBaseMapper().deleteById(ruleId);
    }

}
