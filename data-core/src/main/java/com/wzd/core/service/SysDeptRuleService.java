package com.wzd.core.service;

import com.wzd.core.entity.SysDeptRule;
import com.wzd.core.mapper.SysDeptRuleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzd
 * @since 2019-07-01
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class SysDeptRuleService extends ServiceImpl<SysDeptRuleMapper, SysDeptRule> {

}
