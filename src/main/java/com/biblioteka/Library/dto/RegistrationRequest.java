package com.biblioteka.Library.dto;

import com.biblioteka.Library.Registration.AppRoles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationRequest {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
    private LocalDate date;
}
