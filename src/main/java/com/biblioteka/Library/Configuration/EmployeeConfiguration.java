package com.biblioteka.Library.Configuration;

import com.biblioteka.Library.Repository.EmployeeRepository;
import com.biblioteka.Library.Service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeConfiguration {

    @Bean
    public EmployeeService employeeServicee() {
        return new EmployeeService();
    }

    @Bean
    public ModelMapper modelMapperr() {
        return new ModelMapper();
    }

}
