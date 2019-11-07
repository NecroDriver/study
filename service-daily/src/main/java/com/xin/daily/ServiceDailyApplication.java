package com.xin.daily;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.xin")
public class ServiceDailyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDailyApplication.class, args);
    }

}
