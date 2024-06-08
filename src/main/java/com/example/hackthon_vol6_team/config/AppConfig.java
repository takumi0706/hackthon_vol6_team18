package com.example.hackthon_vol6_team.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertyResolver;

@Configuration
public class AppConfig {

    @Bean
    public PropertyResolver propertyResolver(Environment environment) {
        return environment;
    }
}
