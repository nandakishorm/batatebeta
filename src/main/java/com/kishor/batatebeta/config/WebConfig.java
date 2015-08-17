package com.kishor.batatebeta.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Nandakishor on 8/17/2015.
 */

@Configuration
@ComponentScan(basePackages = "com.kishor.batatebeta.rest")
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
}
