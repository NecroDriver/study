package com.xin.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 启动类
 *
 * @author mafh
 */
@SpringBootApplication
@EnableEurekaServer
public class ServiceEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceEurekaApplication.class, args);
    }

}
