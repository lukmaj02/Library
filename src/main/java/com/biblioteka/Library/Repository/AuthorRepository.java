package com.biblioteka.Library.Repository;

import com.biblioteka.Library.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);
    boolean existsByFirstNameAndLastName(String firstName, String lastName);
}
