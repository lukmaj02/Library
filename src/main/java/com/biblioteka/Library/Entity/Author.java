package com.biblioteka.Library.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name ="author")
public class Author {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @OneToMany
    @JoinColumn(name = "Book_id", referencedColumnName = "id")
    private List<Book> books;
}
