package com.biblioteka.Library.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import javax.management.ConstructorParameters;

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
    @NotNull
    private String imie;
    @NotNull
    private String nazwisko;
    @NotNull
    private Integer wiek;
    @NotNull
    private String email;
    @NotNull
    private String telefon;

    public Employee(String imie, String nazwisko, Integer wiek, String email, String telefon) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wiek = wiek;
        this.email = email;
        this.telefon = telefon;
    }
}
