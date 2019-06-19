package com.wzd.core.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

    @TableId(value = "id",type = IdType.ID_WORKER_STR)
    private String id;

    private Date createTm = new Date();

    private Date updateTm;

    private String isDelete;
}
