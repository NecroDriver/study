package com.xin.swagger.configuration;

import com.xin.web.pojo.Context;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
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
public class Swagger2AutoConfiguration {

    private final Swagger2Properties swagger2Properties;

    public Swagger2AutoConfiguration(Swagger2Properties swagger2Properties) {
        this.swagger2Properties = swagger2Properties;
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 将api的元信息设置为包含在json resource listing响应中
                .apiInfo(apiInfo())
                // 启动用于api选择的生成器
                .select()
                // 为当前包路径
                .apis(RequestHandlerSelectors.basePackage(swagger2Properties.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                // 忽略固定参数
                .ignoredParameterTypes(Context.class);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //文档标题
                .title(swagger2Properties.getTitle())
                //文档描述
                .description(swagger2Properties.getDescription())
                //联系人
                .contact(new Contact(swagger2Properties.getContact().getName(), swagger2Properties.getContact().getUrl(), swagger2Properties.getContact().getEmail()))
                //版本号
                .version(swagger2Properties.getVersion())
                //更新此API的许可证信息
                .license(swagger2Properties.getLicense())
                //更新此API的许可证Url
                .licenseUrl(swagger2Properties.getLicenseUrl())
                .build();
    }
}
