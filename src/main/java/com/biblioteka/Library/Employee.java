package com.biblioteka.Library;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.beans.BeanProperty;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Employee {
    @Id
    private Integer id;
    private String imie;
    private String nazwisko;
    private Integer wiek;
    private String email;
    private String telefon;
}
