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
public class ExcelData extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * sheet的id
     */
    private String sheetId;

    /**
     * 所属车间的id
     */
    private String deptId;

    /**
     * 行数据
     */
    private String rowData;


}
