package com.biblioteka.Library.Repository;

import com.biblioteka.Library.Entity.Book;
import com.biblioteka.Library.Entity.User;
import com.biblioteka.Library.Entity.UserBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
@Repository
@Transactional(readOnly = true)
public interface UserBooksRepository extends JpaRepository<UserBooks, String> {
    Optional<UserBooks> findByUserAndBook(User user, Book book);
}
