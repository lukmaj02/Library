package com.biblioteka.Library.Repository;

import com.biblioteka.Library.Entity.Book;
import com.biblioteka.Library.Entity.User;
import com.biblioteka.Library.Entity.UserBooks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBooksRepository extends JpaRepository<UserBooks, Integer> {
    Optional<UserBooks> findByUserAndBook(User user, Book book);
}
