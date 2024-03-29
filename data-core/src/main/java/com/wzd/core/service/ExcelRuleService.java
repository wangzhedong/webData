package com.wzd.core.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzd.core.entity.ExcelRule;
import com.wzd.core.mapper.ExcelRuleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 删除规则，以及规则与部门的关系数据
     * @param ruleId
     */
    public void delRule(String ruleId){
        this.getBaseMapper().deleteById(ruleId);
    }

}
