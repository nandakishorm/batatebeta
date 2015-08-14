package com.kishor.batatebeta;

import com.kishor.batatebeta.ui.Dashboard;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@PropertySource(value = "classpath:app.properties", ignoreResourceNotFound = true)
@ComponentScan(basePackages = "com.kishor.batatebeta.config")
public class BatatebetaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatatebetaApplication.class, args);
    }

//    @Bean
//    public EmbeddedServletContainerFactory servletContainer() {
//        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
//        factory.setPort(Integer.valueOf(8080));
//        factory.setContextPath("/test");
//        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notfound.html"));
//        return factory;
//    }
}

@SpringUI
class MainUI extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Dashboard dashboard = new Dashboard();
        setContent(dashboard);
    }
}
