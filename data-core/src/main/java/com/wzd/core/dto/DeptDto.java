package com.wzd.core.dto;

import lombok.Data;

import java.util.List;

@Data
public class DeptDto {

    private String id;

    private String deptName;

    private String deptNo;

    private List<String> ruleIds;
}
