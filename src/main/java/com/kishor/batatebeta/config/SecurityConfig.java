package com.kishor.batatebeta.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by Nandakishor on 8/20/2015.
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@ComponentScan(basePackages = "com.kishor.batatebeta.auth", scopedProxy = ScopedProxyMode.INTERFACES)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("batateUserDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService);

        /*authenticationManagerBuilder.inMemoryAuthentication().withUser("user").password("user").roles("USER");
        authenticationManagerBuilder.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
        authenticationManagerBuilder.inMemoryAuthentication().withUser("kishor").password("kishor").roles("KISHOR");*/
    }

    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests().anyRequest().fullyAuthenticated()
                .and()
                .formLogin()
                .and()
                .logout().invalidateHttpSession(true).logoutUrl("/applogout");

        httpSecurity.httpBasic();
        httpSecurity.csrf().disable();

        /*httpSecurity.authorizeRequests()
                .antMatchers("/users*//**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/admins*//**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_KISHOR')")
                .and().formLogin();*/
    }
}
