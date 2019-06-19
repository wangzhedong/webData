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
public class ExcelSheet extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 表格模板id
     */
    private String excelId;

    /**
     * sheet名称
     */
    private String sheetName;


}
