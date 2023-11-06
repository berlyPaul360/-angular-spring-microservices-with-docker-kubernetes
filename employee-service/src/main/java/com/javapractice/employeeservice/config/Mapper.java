package com.javapractice.employeeservice.config;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class Mapper {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
