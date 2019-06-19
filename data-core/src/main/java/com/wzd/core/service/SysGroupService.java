package com.wzd.core.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzd.core.entity.SysGroup;
import com.wzd.core.mapper.SysGroupMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class})
public class SysGroupService extends ServiceImpl<SysGroupMapper, SysGroup> {
}
