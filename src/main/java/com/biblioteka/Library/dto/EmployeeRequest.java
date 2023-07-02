package com.biblioteka.Library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeRequest {
    private String name;
    private String surname;
    private String email;
    private Integer age;
    private String phoneNumber;
}
