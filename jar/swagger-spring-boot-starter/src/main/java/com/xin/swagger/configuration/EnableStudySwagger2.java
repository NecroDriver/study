package com.xin.swagger.configuration;

import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.annotation.*;

/**
 * 启用注解EnableStudySwagger2
 * @author creator mafh 2019/11/8 16:45
 * @author updater
 * @version 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(Swagger2AutoConfiguration.class)
@EnableSwagger2
public @interface EnableStudySwagger2 {
}
