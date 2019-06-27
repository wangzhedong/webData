package com.wzd.core.constant;

public enum ButtonTypeEnum {

    ADD("新增","add"),
    DELETE("删除","delete"),
    UPDATE("更新","update"),
    QUERY("查询","query");
    private String lable;
    private String value;
    ButtonTypeEnum(String lable,String value) {
        this.lable = lable;
        this.value = value;
    }

    public String getLable() {
        return lable;
    }

    public String getValue() {
        return value;
    }
}
