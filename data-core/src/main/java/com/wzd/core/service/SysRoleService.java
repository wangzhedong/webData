package com.wzd.core.service;

import com.wzd.core.entity.SysRole;
import com.wzd.core.mapper.SysRoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzd
 * @since 2019-05-14
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class SysRoleService extends ServiceImpl<SysRoleMapper, SysRole> {

}
