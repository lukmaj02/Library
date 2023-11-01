package com.biblioteka.Library.dto;

import com.biblioteka.Library.Security.config.AppRoles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegistrationRequest {
    private String name;
    private String surname;
    private String username;
    private String password;
    private String phoneNumber;
    private LocalDate date;
    private AppRoles role;
}
