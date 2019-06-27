package com.wzd.core.entity;

import com.wzd.core.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *  模块
 * </p>
 *
 * @author wzd
 * @since 2019-06-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysModule extends BaseEntity {

private static final long serialVersionUID=1L;

    private String parentId;

    private String moduleName;

    private String routerPath;

    private String type;


}
