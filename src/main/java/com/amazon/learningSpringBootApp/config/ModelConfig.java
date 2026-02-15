package com.amazon.learningSpringBootApp.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for application beans.
 * 
 * Defines ModelMapper bean for DTO-Entity conversions.
 */
@Configuration
public class ModelConfig {

    /**
     * Creates ModelMapper bean for object mapping.
     * Used to convert between Entity and DTO objects.
     * 
     * @return ModelMapper instance
     */
    @Bean("modelMapper")
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
