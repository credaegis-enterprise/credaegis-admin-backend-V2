package com.credaegis.backend.configuration.json;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonConfig {


    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
