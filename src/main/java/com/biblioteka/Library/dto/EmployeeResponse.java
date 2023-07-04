package com.biblioteka.Library.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeResponse {
    private String name;
    private String surname;
    private String email;
}
