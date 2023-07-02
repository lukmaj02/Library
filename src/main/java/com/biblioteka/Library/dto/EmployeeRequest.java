package com.biblioteka.Library.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeRequest {
    private String imie;
    private String nazwisko;
    private String email;
    private String telefon;
}
