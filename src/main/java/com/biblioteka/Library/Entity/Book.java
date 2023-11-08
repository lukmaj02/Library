package com.biblioteka.Library.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.cache.annotation.CacheConfig;

import java.util.Set;

import static jakarta.persistence.CascadeType.*;

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
