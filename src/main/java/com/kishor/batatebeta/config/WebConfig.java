package com.kishor.batatebeta.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by Nandakishor on 8/17/2015.
 */

@Configuration
@ComponentScan(basePackages = {"com.kishor.batatebeta.rest", "com.kishor.batatebeta.ui"})
@PropertySource(value = {"classpath:app.properties"})
@EnableWebMvc
@EnableAutoConfiguration
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebConfig.class);

    @Value("${spring.view.prefix}")
    private String viewPrefix;

    @Value("${contextPath}")
    private String contextPath;

//    @Value("${serverPort}")
//    private Integer serverPort;

//    @Bean
//    public EmbeddedServletContainerFactory servletContainer() {
//        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
//        factory.setPort(8090);
//        factory.setContextPath(contextPath);
////        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notfound.html"));
//        return factory;
//    }

    @Bean
    public InternalResourceViewResolver viewResolver()
    {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages");
        viewResolver.setSuffix(".jsp");
        //log.info("Image dir = " + dirName);
        return viewResolver;
    }
}
