package com.springboot2.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @author Lemon
 * @create 2022-12-10-10:02
 */
@Slf4j
@Configuration
public class MyConfiguration /*implements WebMvcConfigurer*/ {

    //    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//        UrlPathHelper up = new UrlPathHelper();
//        up.setRemoveSemicolonContent(false);
//        configurer.setUrlPathHelper(up);
//    }
    @ConfigurationProperties("spring.datasource")
    @Bean
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        StatViewServlet statViewServlet = new StatViewServlet();
        return new ServletRegistrationBean<>(statViewServlet, "/druid/*");
    }

    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter() {
        WebStatFilter webStatFilter = new WebStatFilter();
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>(webStatFilter);
        bean.setUrlPatterns(Arrays.asList("/*"));
        bean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return bean;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper up = new UrlPathHelper();
                up.setRemoveSemicolonContent(false);
                configurer.setUrlPathHelper(up);
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
            }
        };
    }

}
