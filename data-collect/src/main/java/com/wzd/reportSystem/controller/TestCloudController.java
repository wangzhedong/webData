package com.wzd.reportSystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provide")
public class TestCloudController {

    @GetMapping("/find-one-user")
    public String findAllUser() {
        System.out.println("/find-one-user");
        return "张三";
    }


}
