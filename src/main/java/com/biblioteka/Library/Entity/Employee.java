package com.biblioteka.Library.Entity;

import jakarta.persistence.*;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pracownik")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private String email;
    @Column(name ="phone_number")
    private String phoneNumber;

    public Employee(String name, String surname, Integer age, String email, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
