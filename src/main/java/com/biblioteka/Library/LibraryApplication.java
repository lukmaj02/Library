package com.biblioteka.Library;

import com.biblioteka.Library.Service.BookService;
import com.biblioteka.Library.Service.RegistrationService;
import com.biblioteka.Library.Service.UserService;
import com.biblioteka.Library.dto.AuthorDto;
import com.biblioteka.Library.dto.BookDto;
import com.biblioteka.Library.dto.RegistrationRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

import static com.biblioteka.Library.Security.config.AppRoles.*;

@SpringBootApplication
public class  LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

}
