package com.biblioteka.Library.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    private String ISBN;
    private String title;
    @Column(name = "author_name")
    private String authorName;
    @Column(name = "author_last_name")
    private String authorLastName;
    @Column(name = "publication_date")
    private Integer publicationDate;
    private Integer quantity;
    private Double price;
    @Column(name = "lang")
    private String language;
}
