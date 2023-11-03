package com.biblioteka.Library;

import com.biblioteka.Library.Service.BookService;
import com.biblioteka.Library.Service.RegistrationService;
import com.biblioteka.Library.Service.UserService;
import com.biblioteka.Library.dto.*;
import com.biblioteka.Library.dto.Mapper.BookMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.print.Book;
import java.time.LocalDate;

import static com.biblioteka.Library.Security.config.AppRoles.*;


@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
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
                    .build();

            var user = RegistrationRequest.builder()
                    .name("user")
                    .surname("user")
                    .username("user@user.com")
                    .password("user")
                    .phoneNumber("987654321")
                    .date(LocalDate.of(2000,1,1))
                    .build();

            var user1 = RegistrationRequest.builder()
                    .name("user1")
                    .surname("user1")
                    .username("user1@user1.com")
                    .password("user1")
                    .phoneNumber("987654321")
                    .date(LocalDate.of(1990,1,1))
                    .build();

            var employee = RegistrationRequest.builder()
                    .name("employee")
                    .surname("employee")
                    .username("employee@employee.com")
                    .password("employee")
                    .phoneNumber("192837475")
                    .date(LocalDate.of(1999,1,1))
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

            var book3 = BookDto.builder()
                    .title("Władca Pierścieni")
                    .publicationDate(2000)
                    .isbn("1984327482394")
                    .author(AuthorDto.builder()
                            .firstName("J.R.R")
                            .lastName("Tolkien")
                            .build())
                    .quantity(7)
                    .build();

            registrationService.registerEmployeeOrAdmin(admin,"ADMIN");
            registrationService.registerEmployeeOrAdmin(employee, "EMPLOYEE");
            registrationService.register(user);
            registrationService.register(user1);

            userService.enableUser(employee.getUsername());
            userService.enableUser(user.getUsername());
            userService.enableUser(admin.getUsername());
            userService.enableUser(user1.getUsername());

            bookService.addBook(BookMapper.map(book1));
            bookService.addBook(BookMapper.map(book2));
            bookService.addBook(BookMapper.map(book3));
        };
      }
}
