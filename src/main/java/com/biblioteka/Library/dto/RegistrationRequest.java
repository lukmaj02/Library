package com.biblioteka.Library.dto;

import com.biblioteka.Library.Security.config.AppRoles;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@Builder
public class RegistrationRequest {
    private String name;
    private String surname;
    private String username;
    private String password;
    private String phoneNumber;
    private LocalDate date;
}
