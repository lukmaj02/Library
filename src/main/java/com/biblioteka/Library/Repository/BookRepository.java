package com.biblioteka.Library.Repository;

import com.biblioteka.Library.Entity.Book;
import com.biblioteka.Library.Entity.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByTitle(String title);
    boolean existsByIsbn(String ISBN);
    boolean existsById(@NonNull Integer id);
    Optional<Collection<Book>> findByUsers(User user);

}
