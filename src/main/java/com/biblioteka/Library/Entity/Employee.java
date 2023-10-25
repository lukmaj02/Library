package com.biblioteka.Library.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pracownik")
public class Employee {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String surname;
    private LocalDate date;
    private String email;
    private String password;
    @Column(name ="phone_number")
    private String phoneNumber;

}
