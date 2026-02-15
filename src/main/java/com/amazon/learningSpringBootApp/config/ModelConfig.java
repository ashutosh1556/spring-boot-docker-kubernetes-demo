package com.amazon.learningSpringBootApp.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class ModelConfig {

    @Bean("modelMapper")
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
