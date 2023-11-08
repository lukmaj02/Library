package com.biblioteka.Library.DTO;

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
