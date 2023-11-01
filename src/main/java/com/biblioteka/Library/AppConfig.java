package com.biblioteka.Library;

import com.biblioteka.Library.Service.BookService;
import com.biblioteka.Library.Service.RegistrationService;
import com.biblioteka.Library.Service.UserService;
import com.biblioteka.Library.dto.AuthorDto;
import com.biblioteka.Library.dto.BookDto;
import com.biblioteka.Library.dto.EmployeeResponse;
import com.biblioteka.Library.dto.RegistrationRequest;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

import static com.biblioteka.Library.Security.config.AppRoles.*;


@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public CommandLineRunner commandLineRunner(RegistrationService registrationService,
                                               UserService userService,
                                               BookService bookService){
        return args ->{
            var admin = RegistrationRequest.builder()
                    .name("admin")
                    .surname("admin")
                    .username("admin@admin.com")
                    .password("admin")
                    .phoneNumber("123456789")
                    .date(LocalDate.of(2002,1,1))
                    .role(ADMIN)
                    .build();

            var user = RegistrationRequest.builder()
                    .name("user")
                    .surname("user")
                    .username("user@user.com")
                    .password("user")
                    .phoneNumber("987654321")
                    .date(LocalDate.of(2000,1,1))
                    .role(USER)
                    .build();

            var employee = RegistrationRequest.builder()
                    .name("employee")
                    .surname("employee")
                    .username("employee@employee.com")
                    .password("employee")
                    .phoneNumber("192837475")
                    .date(LocalDate.of(1999,1,1))
                    .role(EMPLOYEE)
                    .build();


            var author = AuthorDto.builder()
                    .firstName("J.K.")
                    .lastName("Rowling")
                    .build();

            var book1 = BookDto.builder()
                    .title("Harry Potter i Kamien Filozoficzny")
                    .isbn("123456789")
                    .author(author)
                    .quantity(10)
                    .publicationDate(1990)
                    .build();

            var book2 = BookDto.builder()
                    .title("Harry Potter i Komnata Tajemnic")
                    .isbn("7564782937")
                    .author(author)
                    .quantity(15)
                    .publicationDate(1994)
                    .build();

            registrationService.register(admin);
            registrationService.register(user);
            registrationService.register(employee);

            userService.enableUser(employee.getUsername());
            userService.enableUser(user.getUsername());
            userService.enableUser(admin.getUsername());

            bookService.addBook(book1);
            bookService.addBook(book2);
        };
    }
}
