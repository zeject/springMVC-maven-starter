package com.ct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by zeject on 2017/3/1.
 */
@Configuration
@PropertySource(value = "classpath:/config.properties", ignoreResourceNotFound = true)
public class Propties {

    @Value("${text}")
    public String text;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
