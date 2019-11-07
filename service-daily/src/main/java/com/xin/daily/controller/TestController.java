package com.xin.daily.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author creator mafh 2019/11/5 13:30
 * @author updater
 * @version 1.0.0
 */
@RestController
@RequestMapping("/test")
public class TestController {


    @RequestMapping("/testString")
    public String testString(String str) {
        return str;
    }
}
