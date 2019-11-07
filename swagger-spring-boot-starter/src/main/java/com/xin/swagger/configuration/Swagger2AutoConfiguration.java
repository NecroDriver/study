package com.xin.swagger.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * ConditionalOnWebApplication web应用才生效
 * Configuration 声明是一个配置类
 * EnableConfigurationProperties(Swagger2Properties.class) 自动装配这个properties类，读取yaml自定义内容
 *
 * @author creator mafh 2019/11/7 16:05
 * @author updater
 * @version 1.0.0
 */
@ConditionalOnClass(Docket.class)
@EnableConfigurationProperties(Swagger2Properties.class)
@Configuration
public class Swagger2AutoConfiguration extends WebMvcConfigurationSupport {

    private final Swagger2Properties swagger2Properties;

    public Swagger2AutoConfiguration(Swagger2Properties swagger2Properties) {
        this.swagger2Properties = swagger2Properties;
    }

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                // 将api的元信息设置为包含在json resourcelisting响应中
                .apiInfo(apiInfo())
                ;
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title(swagger2Properties.getTitle())
                .description(swagger2Properties.getDescription())
                .contact(new Contact(swagger2Properties.getContact().getName(),swagger2Properties.getContact().getEmail(), swagger2Properties.getContact().getUrl()))
                .version(swagger2Properties.getVersion())
                .license(swagger2Properties.getLicense())
                .licenseUrl(swagger2Properties.getLicenseUrl())
                .build();
    }
}
