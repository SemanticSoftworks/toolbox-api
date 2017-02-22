package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Created by dani on 2017-02-22.
 */
@EnableWebSecurity
public class Security {

    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .httpBasic().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .authorizeRequests()
                    .antMatchers("/user/**").permitAll()
                    .antMatchers("/ad/**").permitAll()
                    .antMatchers("/photo/**").permitAll()
                    .antMatchers("/admin/**").permitAll()
                    .antMatchers("/transaction/**").permitAll();
        }
    }
}
