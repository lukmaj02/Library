package com.biblioteka.Library.Entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    @Column(name = "publication_date")
    private Integer publicationDate;
    @Column(name ="author_id", nullable = false)
    private Integer authorId;
    private String ISBN;
}
