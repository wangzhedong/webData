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
public class ExcelHeader extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * sheet的id
     */
    private String sheetId;

    /**
     * 表头名称
     */
    private String headerName;

    /**
     * 表头的key,唯一不重复
     */
    private String headerKey;

    /**
     * 是横表头，还是竖表头
     */
    private String type;

    /**
     * 权限[不可读，可读，可编辑]
     */
    private String auth;

    /**
     * 拥有者[所有人，[userids]]
     */
    private String owner;

    /**
     * 记录表头的位置
     */
    private Integer position;


}
