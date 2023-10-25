package com.biblioteka.Library.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.List;

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
    private Integer quantity;
    private String isbn;

    @OneToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @JsonBackReference
    private Author author;

    @ManyToMany(mappedBy = "users_books")
    @JsonIgnore
    private List<User> users;
}
