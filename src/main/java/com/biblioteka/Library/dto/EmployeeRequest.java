package com.biblioteka.Library.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeRequest {
    private String imie;
    private String nazwisko;
    private Integer wiek;
    private String email;
    private String telefon;
}
