package com.wzd.core.entity;

import com.wzd.core.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 操作类
 * </p>
 *
 * @author wzd
 * @since 2019-06-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysOperate extends BaseEntity {

private static final long serialVersionUID=1L;

    private String moduleId;

    private String operateName;

    private String requestUrl;

    private String type;


}
