package com.biblioteka.Library.dto;

import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeRequest {
    private String name;
    private String surname;
    private String email;
    private LocalDate date;
    private String phoneNumber;
}
