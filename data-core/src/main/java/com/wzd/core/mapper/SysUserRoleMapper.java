package com.wzd.core.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.wzd.core.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wzd
 * @since 2019-05-14
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 物理删除用户角色关系表
     * @param wrapper
     * @return
     */
    int physicsDelete(@Param("ew") Wrapper<SysUserRole> wrapper);

    void deleteAll();
}
