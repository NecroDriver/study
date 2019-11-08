package com.xin.daily;

import com.xin.swagger.configuration.EnableStudySwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.xin")
@EnableStudySwagger2
public class ServiceDailyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDailyApplication.class, args);
    }

}
