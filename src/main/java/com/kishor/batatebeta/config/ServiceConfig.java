package com.kishor.batatebeta.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Nandakishor on 8/17/2015.
 */

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan(basePackages = "com.kishor.batatebeta.core.service", scopedProxy = ScopedProxyMode.INTERFACES)
public class ServiceConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceConfig.class);
}
