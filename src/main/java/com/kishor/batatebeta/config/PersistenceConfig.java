package com.kishor.batatebeta.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.beans.PropertyVetoException;

/**
 * Created by Nandakishor on 8/11/2015.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.kishor.batatebeta.core.repository")
@EntityScan(basePackages = "com.kishor.batatebeta.core.domain")
@PropertySource(value = { "classpath:jpa.properties" })
@EnableTransactionManagement(proxyTargetClass = true)
public class PersistenceConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceConfig.class);

    @Value("${connection.driver_class}")
    private String driverClass;
    @Value("${connection.url}")
    private String jdbcUrl;
    @Value("${connection.username}")
    private String user;
    @Value("${connection.password}")
    private String password;
    @Value("${minPoolSize}")
    private String minPoolSize;
    @Value("${maxPoolSize}")
    private String maxPoolSize;
    @Value("${checkoutTimeout}")
    private String checkoutTimeout;
    @Value("${maxStatements}")
    private String maxStatements;
    @Value("${idleConnectionTestPeriod}")
    private String idleConnectionTestPeriod;
    @Value("${preferredTestQuery}")
    private String preferredTestQuery;
    @Value("${dialect}")
    private String dialect;
    @Value("${show_sql}")
    private String showSql;
    @Value("${generateDdl}")
    private String generateDdl;

    @Bean
    public HikariDataSource dataSource() throws PropertyVetoException
    {
        HikariConfig config = new HikariConfig();
        config.setMaximumPoolSize(Integer.parseInt(maxPoolSize));
        config.setDriverClassName(driverClass);
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(user);
        config.setPassword(password);

        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws PropertyVetoException
    {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter()
    {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(Boolean.parseBoolean(showSql));
        jpaVendorAdapter.setGenerateDdl(Boolean.parseBoolean(generateDdl));
        jpaVendorAdapter.setDatabasePlatform(dialect);
        return jpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws PropertyVetoException
    {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        //factoryBean.setPackagesToScan("nl.wizzix.fullhouse.core.domain");
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        return factoryBean;
    }
}
