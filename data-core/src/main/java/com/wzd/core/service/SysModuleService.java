package com.wzd.core.service;

import com.wzd.core.entity.SysModule;
import com.wzd.core.mapper.SysModuleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzd
 * @since 2019-06-27
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class SysModuleService extends ServiceImpl<SysModuleMapper, SysModule> {

}
