package com.biblioteka.Library.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeRequest {
    private String name;
    private String surname;
    private String email;
    private Integer age;
    private String phoneNumber;
}
