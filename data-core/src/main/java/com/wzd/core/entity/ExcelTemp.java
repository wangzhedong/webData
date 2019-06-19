package com.wzd.core.entity;

import com.wzd.core.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class ExcelTemp extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 规则id，导入的时候选择或者不选规则
     */
    private String ruleId;

    /**
     * 模板发起人id
     */
    private String authorId;

    /**
     * 完成人id或者部门id{"dept":["id1","id2"]}}
     */
    private String finishId;

    /**
     * 模板名称
     */
    private String tempName;

    /**
     * 是否发布
     */
    private String isPublish;

    /**
     * 是否完成填写
     */
    private String isFinish;


}
