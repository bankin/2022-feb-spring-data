package com.example.workshop1.config;


import com.example.workshop1.util.MyValidator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@Configuration
public class BeanConfiguration {

    @Bean
    public MyValidator getValidator() {
        return new MyValidator();
    }

    @Bean(name = "default")
    @Primary
    public ModelMapper getModelMapper() {
        ModelMapper mapper = new ModelMapper();
        return mapper;
    }

    @Bean(name = "withLocalDate")
    public ModelMapper getAltModelMapper() {
        ModelMapper result = new ModelMapper();

        result.addConverter((Converter<String, LocalDate>) context -> {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(context.getSource(), format);
        });

        return result;
    }

    @Bean
    public Gson getGson() {
        return new GsonBuilder()
            .setPrettyPrinting()
            .create();
    }

}
