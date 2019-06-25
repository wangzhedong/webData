package com.wzd.core.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable,Cloneable {

    @TableId(value = "id",type = IdType.ID_WORKER_STR)
    protected String id;

    protected Date createTm = new Date();

    protected Date updateTm;

    @TableLogic(value = "0",delval = "1")
    protected String isDelete;
}
