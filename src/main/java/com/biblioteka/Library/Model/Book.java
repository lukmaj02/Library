package com.biblioteka.Library.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer id;
    @Column(name = "book_title")
    private String title;
    @Column(name = "publication_date")
    private Integer publicationDate;
    @Column(name = "book_quantity")
    private Integer quantity;
    @Column(name = "book_isbn")
    private String isbn;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private BookTypes type;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @JsonBackReference
    private Author author;

    @OneToMany(mappedBy = "book")
    private Set<UserBooks> userBooks;
}
