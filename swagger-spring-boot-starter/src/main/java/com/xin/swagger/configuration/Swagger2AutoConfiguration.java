package com.xin.swagger.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
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
public class Swagger2AutoConfiguration extends WebMvcConfigurationSupport {

    private final Swagger2Properties swagger2Properties;

    public Swagger2AutoConfiguration(Swagger2Properties swagger2Properties) {
        this.swagger2Properties = swagger2Properties;
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 将api的元信息设置为包含在json resource listing响应中
                .apiInfo(apiInfo())
                //启动用于api选择的生成器
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage(swagger2Properties.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //文档标题
                .title(swagger2Properties.getTitle())
                //文档描述
                .description(swagger2Properties.getDescription())
                //联系人
                .contact(new Contact(swagger2Properties.getContact().getName(), swagger2Properties.getContact().getEmail(), swagger2Properties.getContact().getUrl()))
                //版本号
                .version(swagger2Properties.getVersion())
                //更新此API的许可证信息
                .license(swagger2Properties.getLicense())
                //更新此API的许可证Url
                .licenseUrl(swagger2Properties.getLicenseUrl())
                .build();
    }

    /**
     * 静态资源配置(默认)
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 静态资源路径
        registry.addResourceHandler(swagger2Properties.getBasePath()).addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }

    /**
     * 解决跨域问题
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
                .allowCredentials(true).maxAge(3600);
    }
}
