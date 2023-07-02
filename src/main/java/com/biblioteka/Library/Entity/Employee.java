package com.biblioteka.Library.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import javax.management.ConstructorParameters;

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
    private String imie;
    private String nazwisko;
    private Integer wiek;
    private String email;
    private String telefon;

    public Employee(String imie, String nazwisko, Integer wiek, String email, String telefon) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wiek = wiek;
        this.email = email;
        this.telefon = telefon;
    }
}
