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
public class SysUser extends BaseEntity {

    private static final long serialVersionUID=1L;

    private String groupId;

    private String userName;

    private String uemail;

    private String tel;

    private String password;

    private String sex;

    private String deptId;

    @TableField(exist=false)
    private List<SysUserRole> userRoles;

}
