package com.xin.daily;

import com.xin.swagger.configuration.EnableStudySwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 日常学习
 *
 * @author mafh
 */
@SpringBootApplication(scanBasePackages = "com.xin")
@EnableStudySwagger2
public class ServiceDailyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDailyApplication.class, args);
    }

}
