package com.kishor.batatebeta.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nandakishor on 8/12/2015.
 */
@Configuration
public class ApplicationConfig {
    private static final Logger log = LoggerFactory.getLogger(ApplicationConfig.class);

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties()
    {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        Resource[] resources = new ClassPathResource[]{new ClassPathResource("app.properties"), new ClassPathResource("jpa.properties")};
        configurer.setLocations(resources);
        configurer.setIgnoreUnresolvablePlaceholders(true);
        log.info("app.properties loaded.");
        return configurer;
    }

    @Bean
    public Mapper mapper()
    {
        DozerBeanMapper mapper = new DozerBeanMapper();
        List<String> dozerMappingFiles = new ArrayList<String>();
        //dozerMappingFiles.add("dozermapping.xml");
        mapper.setMappingFiles(dozerMappingFiles);
        return mapper;
    }

//    @Bean
//    public CommonsMultipartResolver multipartResolver()
//    {
//        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//        resolver.setMaxUploadSize(104857600);
//        return resolver;
//    }

    @Bean
    public ObjectMapper objectMapper()
    {
        return new ObjectMapper();
    }
}
