package com.xin.swagger.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Swagger属性配置
 *
 * @author creator mafh 2019/11/7 16:05
 * @author updater
 * @version 1.0.0
 */
@ConfigurationProperties(prefix = "swagger")
public class Swagger2Properties {

    /**
     * 文档标题
     */
    private String title = "Spring Boot中使用Swagger2构建RESTFUL APIS";
    /**
     * 文档描述
     */
    private String description = "心心念念，必有回响";
    /**
     * 联系人
     */
    private Contact contact = new Contact();
    /**
     * 版本
     */
    private String version = "0.0.1";
    /**
     * 许可证
     */
    private String license = "";
    /**
     * 许可证url
     */
    private String licenseUrl = "";
    /**
     * swagger扫描地址
     */
    private String basePackage = "com.xin";
    /**
     * 需要处理的基础URL规则，默认：/**
     */
    private String basePath = "/**";
    /**
     * 联系人
     */
    public static class Contact {
        /**
         * 维护人名
         */
        private String name = "necroDriver";
        /**
         * 维护人email
         */
        private String email = "NecroDriver@foxmail.com";
        /**
         * 维护人url
         */
        private String url = "https://github.com/necroDriver";

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }
}
