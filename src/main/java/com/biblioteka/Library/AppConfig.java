package com.biblioteka.Library;

import com.biblioteka.Library.DTO.AuthorDto;
import com.biblioteka.Library.DTO.BookDto;
import com.biblioteka.Library.DTO.Mapper.BookMapper;
import com.biblioteka.Library.DTO.RegistrationRequest;
import com.biblioteka.Library.Security.config.AppRoles;
import com.biblioteka.Library.Service.BookService;
import com.biblioteka.Library.Service.RegistrationService;
import com.biblioteka.Library.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.time.LocalDate;
import java.util.Properties;


@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("lmajcher02@gmail.com");
        mailSender.setPassword("gtii xkuh eoeq iidp");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public CommandLineRunner commandLineRunner(RegistrationService registrationService,
                                               UserService userService,
                                               BookService bookService){
        if(userService.userExistsByUsername("admin@admin.com")) return null;
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

            registrationService.register(admin, AppRoles.ADMIN);
            registrationService.register(employee, AppRoles.EMPLOYEE);
            registrationService.register(user, AppRoles.USER);
            registrationService.register(user1, AppRoles.USER);

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
