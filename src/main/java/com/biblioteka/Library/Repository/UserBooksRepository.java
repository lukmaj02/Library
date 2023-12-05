package com.biblioteka.Library.Repository;

import com.biblioteka.Library.Model.Book;
import com.biblioteka.Library.Model.User;
import com.biblioteka.Library.Model.UserBooks;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
@Repository
@Transactional(readOnly = true)
public interface UserBooksRepository extends JpaRepository<UserBooks, Integer> {
    Optional<UserBooks> findByUserAndBook(User user, Book book);
}
