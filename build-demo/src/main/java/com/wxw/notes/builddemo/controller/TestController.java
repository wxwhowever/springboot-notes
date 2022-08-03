package com.wxw.notes.builddemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuxiongwei
 * @date 2022/1/12 11:02
 * @Description
 */
@RestController
public class TestController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello 123";
    }


}
