package com.xin.work.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author creator mafh 2019/10/21 14:20
 * @author updater
 * @version 1.0.0
 */
@RestController
public class TestController {

    @RequestMapping("/hello")
    public String getIndex(@RequestParam String name) {
        return name + ",this is a test controller";
    }
}
