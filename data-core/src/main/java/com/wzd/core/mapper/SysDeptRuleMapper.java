package com.wzd.core.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzd.core.entity.SysDeptRule;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  部门与规则中间表Mapper 接口
 * </p>
 *
 * @author wzd
 * @since 2019-07-01
 */
public interface SysDeptRuleMapper extends BaseMapper<SysDeptRule> {

    /**
     * 物理删除用户角色关系表
     * @param wrapper
     * @return
     */
    int physicsDelete(@Param("ew") Wrapper<SysDeptRule> wrapper);
}
