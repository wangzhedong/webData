package com.wzd.reportSystem.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.wzd.core.entity.ExcelRule;
import com.wzd.core.entity.ExcelTemp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  报表模板管理
 * </p>
 *
 * @author wzd
 * @since 2019-05-14
 */
@RestController
@RequestMapping("/excelTemp")
public class ExcelTempController {

    @PostMapping("add")
    public R add(@RequestBody ExcelTemp temp){

        return R.ok(null);
    }



    private R checkTemp(ExcelTemp temp,String type){
        if(StringUtils.isBlank(temp.getAuthorId())){
            return R.failed("发起人不能为空！");
        }
        if(StringUtils.isBlank(temp.getTempName())){
            return R.failed("模板名称不能为空！");
        }
        return null;
    }

}

