package com.beam.sample.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@PropertySource("classpath:application.properties")
@Configuration
@ComponentScan(basePackages = {"com.beam.sample.business", "com.beam.sample.beans"})
@Import({DbConfig.class, SecurityConfig.class})
public class AppConfig {

    @Autowired
    private Environment environment;

    private Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    public ShaPasswordEncoder passwordEncoder() {
        return new ShaPasswordEncoder();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return  new ObjectMapper();
    }

}
