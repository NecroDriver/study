package com.xin.jdbc.config;

import com.alibaba.druid.pool.DruidAbstractDataSource;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Servlet;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置druid需要的配置类，引入application.properties文件中以spring.datasource开头的信息
 * 会被springboot扫描然后将相应的service加到容器中
 * 因此需要在application.properties文件中配置相关信息。
 *
 * @author creator mafh 2019/11/5 16:56
 * @author updater
 * @version 1.0.0
 */
@Configuration
public class DruidConfig {

    private final Logger logger = LoggerFactory.getLogger(DruidConfig.class);

    /**
     * 将DataSource对象的实现类变为了DruidDataSource对象
     *
     * @return DataSource对象
     */
//    @Bean(destroyMethod = "close",initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        logger.info("==== init druid data source ====");
        DruidDataSource druidDataSource = new DruidDataSource();
        // 连接池配置(通常来说，只需要修改initialSize、minIdle、maxActive
        if (druidDataSource.getInitialSize() == DruidAbstractDataSource.DEFAULT_INITIAL_SIZE) {
            druidDataSource.setInitialSize(5);
        }
        if (druidDataSource.getMinIdle() == DruidAbstractDataSource.DEFAULT_MIN_IDLE) {
            druidDataSource.setMinIdle(5);
        }
        if (druidDataSource.getMaxActive() == DruidAbstractDataSource.DEFAULT_MAX_ACTIVE_SIZE) {
            druidDataSource.setMaxActive(20);
        }
        return druidDataSource;
    }

    /**
     * 配置druid过滤规则
     *
     * @return bean
     */
    @Bean
    public FilterRegistrationBean<WebStatFilter> filterRegistrationBean() {
        logger.info("==== init filter registration ====");
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        Map<String, String> initParam = new HashMap<>();
        initParam.put("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.setInitParameters(initParam);
        return filterRegistrationBean;
    }

    /**
     * druid监控页面配置
     * 展示Druid的统计信息,StatViewServlet的用途包括：1.提供监控信息展示的html页面2.提供监控信息的JSON API
     *
     * @return bean
     */
    @Bean
    public ServletRegistrationBean<Servlet> druidServlet() {
        logger.info("==== init Druid Servlet Config =====");
        ServletRegistrationBean<Servlet> servletRegistrationBean = new ServletRegistrationBean<>();
        // 配置一个拦截器
        servletRegistrationBean.setServlet(new StatViewServlet());
        // 指定拦截器只拦截druid管理页面的请求
        servletRegistrationBean.addUrlMappings("/druid/*");
        HashMap<String, String> initParam = new HashMap<>();
        // 用户名
        initParam.put("loginUsername", "admin");
        // 密码
        initParam.put("loginPassword", "admin");
        // 是否允许重置druid的统计信息
        initParam.put("resetEnable", "false");
        // 配置的格式 <IP>或者<IP>/<SUB_NET_MASK_size>其中128.242.127.1/24 24表示，前面24位是子网掩码，比对的时候，前面24位相同就匹配,不支持IPV6。
        // IP白名单 (没有配置或者为空，则允许所有访问)
        initParam.put("allow", "");
        // IP黑名单 (存在共同时，deny优先于allow)
        initParam.put("deny", "");
        servletRegistrationBean.setInitParameters(initParam);
        return servletRegistrationBean;
    }
}
