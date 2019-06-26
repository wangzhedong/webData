package com.wzd.core.mapper;

import com.wzd.core.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wzd
 * @since 2019-05-14
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("SELECT t_question.*,t_student.`name` FROM SYS_USER u,SYS_USER_ROLE ur WHERE u.user_id = ur.user_id")
    public List<SysUser> queryByPage();
}
