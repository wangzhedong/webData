package com.wzd.core.service;

import com.wzd.core.entity.ExcelTemp;
import com.wzd.core.mapper.ExcelTempMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  excel模板管理
 * </p>
 *
 * @author wzd
 * @since 2019-05-14
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class ExcelTempService extends ServiceImpl<ExcelTempMapper, ExcelTemp> {

}
