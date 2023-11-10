package com.biblioteka.Library.Repository;

import com.biblioteka.Library.Model.Author;
import com.biblioteka.Library.Model.Book;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByTitle(String title);
    boolean existsByIsbnOrTitle(String isbn, String title);
    boolean existsById(@NonNull Integer id);
    Optional<Set<Book>> findAllByAuthor(Author author);
}
