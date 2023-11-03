package com.biblioteka.Library.Service;

import com.biblioteka.Library.Entity.Author;
import com.biblioteka.Library.Entity.User;
import com.biblioteka.Library.Entity.UserBooks;
import com.biblioteka.Library.Exceptions.NotFoundException.BookNotFoundException;
import com.biblioteka.Library.Repository.UserBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class UserBooksService {
    private final BookService bookService;
    private final UserService userService;
    private final UserBooksRepository userBooksRepository;

    @Autowired
    public UserBooksService(BookService bookService,
                            UserService userService,
                            UserBooksRepository userBooksRepository) {
        this.bookService = bookService;
        this.userService = userService;
        this.userBooksRepository = userBooksRepository;
    }
    public Set<UserBooks> getAllUserBooks(String username){
        var user = (User) userService.loadUserByUsername(username);
        return user.getUserBooks();
    }

    public UserBooks getUserBookWithId(Integer bookId, String username){
        var user = (User) userService.loadUserByUsername(username);
        var book = bookService.getBookById(bookId);
        return userBooksRepository.findByUserAndBook(user,book).orElseThrow(BookNotFoundException::new);
    }
    @Transactional
    public void userReturnsBook(Integer bookId, String username){
        var userBook = getUserBookWithId(bookId, username);
        userBook.setReturnDate(LocalDateTime.now());
        bookService.returnBookById(bookId);
    }

    @Transactional
    public void userBorrowsBook(Integer bookId, String username){
        var user = (User) userService.loadUserByUsername(username);
        var book = bookService.borrowBookById(bookId);

        userBooksRepository.save(UserBooks.builder()
                .user(user)
                .book(book)
                .borrowDate(LocalDateTime.now())
                .expireDate(LocalDateTime.now().plusDays(90))
                .build()
        );
    }

//    public UserBooks getUserBooksWithAuthor(Author author, String surname){
//
//    }
}
