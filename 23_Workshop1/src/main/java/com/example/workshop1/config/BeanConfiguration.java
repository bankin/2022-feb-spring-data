package com.example.workshop1.config;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;

@Configuration
public class BeanConfiguration {

    @Bean(name = "default")
    public ModelMapper getModelMapper() {
        ModelMapper mapper = new ModelMapper();

//        mapper.addConverter(ctx -> , String.class, LocalDate.class);

        return mapper;
    }

    @Bean(name = "alt")
    public ModelMapper getAltModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Gson getGson() {
        return new GsonBuilder()
            .setPrettyPrinting()
            .create();
    }

}
