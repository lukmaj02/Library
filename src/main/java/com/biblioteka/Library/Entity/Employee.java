package com.biblioteka.Library.Entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "pracownik")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "age")
    private Integer age;
    @Column(name = "email")
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
