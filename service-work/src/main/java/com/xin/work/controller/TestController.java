package com.xin.work.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author creator mafh 2019/10/21 14:20
 * @author updater
 * @version 1.0.0
 * @RefreshScope 使用该注解的类，会在接到SpringCloud配置中心配置刷新的时候，自动将新的配置更新到该类对应的字段中。
 */
@RestController
@RefreshScope
public class TestController {

    @Value("${test-name}")
    private String testName;

    @RequestMapping("/hello")
    public String getIndex(@RequestParam String name) {
        return name + ",this is a test controller";
    }

    @RequestMapping("/getConfig")
    public String getConfig() {
        return testName;
    }
}
