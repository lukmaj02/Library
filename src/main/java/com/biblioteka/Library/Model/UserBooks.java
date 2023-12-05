package com.biblioteka.Library.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.*;
import java.time.LocalDate;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_books")
public class UserBooks {
    @Id
    @Column(name = "id", length = 6)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonIgnore
    private Book book;
    private LocalDate reservedDate;
    private LocalDate borrowDate;
    private LocalDate expireDate;
    private LocalDate returnDate;

}
