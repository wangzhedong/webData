package com.wzd.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.wzd.core.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 部门与规则对应的中间关系表
 * </p>
 *
 * @author wzd
 * @since 2019-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysDeptRule extends BaseEntity {

private static final long serialVersionUID=1L;

    private String deptId;

    private String ruleId;

    @TableField(exist=false)
    private String ruleName;


}
