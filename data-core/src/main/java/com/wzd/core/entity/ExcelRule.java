package com.wzd.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.wzd.core.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *  报表匹配规则
 *  一条规则对应一个部门
 *  规则决定了该行数据所属的部门
 * </p>
 *
 * @author wzd
 * @since 2019-05-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ExcelRule extends BaseEntity {

    private static final long serialVersionUID=1L;

    private String deptId;

    private String ruleName;

    private String ruleDetail;

    @TableField(exist=false)
    private String deptName;

}
