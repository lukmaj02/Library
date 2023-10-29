package com.biblioteka.Library.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import java.util.List;
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
    @GeneratedValue
    private Integer id;
    private String title;
    @Column(name = "publication_date")
    private Integer publicationDate;
    private Integer quantity;
    private String isbn;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @JsonBackReference
    private Author author;

    @ManyToMany(mappedBy = "users_books")
    @JsonIgnore
    private Set<User> users;
}
