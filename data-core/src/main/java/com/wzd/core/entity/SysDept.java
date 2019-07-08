package com.wzd.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.wzd.core.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author wzd
 * @since 2019-05-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysDept extends BaseEntity {

    private static final long serialVersionUID=1L;

    private String deptName;

    private String deptNo;

    @TableField(exist=false)
    private ExcelRule excelRule;

}
