/*
 * Copyright (c) 2018 All Rights Reserved, Jinxiudadi
 * @Title: GoodsInfoResource.java
 */
package com.autumn.walelt.controller;

import org.springframework.web.bind.annotation.*;


/**
 * 控制层
 *
 * @author dell
 * @ClassName: GoodsInfoResource
 * @date 2018年11月14日
 * @version: V1.0
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {
    @GetMapping(value = "/test")
    public String test() throws Exception {
        return "fail";
    }
}
