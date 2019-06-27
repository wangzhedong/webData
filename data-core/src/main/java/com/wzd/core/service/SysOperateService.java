package com.wzd.core.service;

import com.wzd.core.entity.SysOperate;
import com.wzd.core.mapper.SysOperateMapper;
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
public class SysOperateService extends ServiceImpl<SysOperateMapper, SysOperate> {

}
