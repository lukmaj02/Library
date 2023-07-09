package com.biblioteka.Library.Repository;

import com.biblioteka.Library.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByTitle(String title);
    boolean existsByIsbn(String ISBN);
}
