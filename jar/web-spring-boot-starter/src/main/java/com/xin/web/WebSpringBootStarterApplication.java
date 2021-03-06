package com.xin.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 允许开启切面代理
 */
@EnableAspectJAutoProxy
@SpringBootApplication
public class WebSpringBootStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSpringBootStarterApplication.class, args);
    }

}
