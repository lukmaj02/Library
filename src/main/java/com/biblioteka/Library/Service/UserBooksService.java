package com.biblioteka.Library.Service;

import com.biblioteka.Library.Model.User;
import com.biblioteka.Library.Model.UserBooks;
import com.biblioteka.Library.Exceptions.NotFoundException.BookNotFoundException;
import com.biblioteka.Library.Exceptions.NotFoundException.UserBookTokenNotFoundException;
import com.biblioteka.Library.Repository.UserBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.Set;

@Service
public class UserBooksService {
    private final BookService bookService;
    private final UserService userService;
    private final UserBooksRepository userBooksRepository;
    private final EmailSenderService emailSenderService;
    private final Random random;

    @Autowired
    public UserBooksService(BookService bookService,
                            UserService userService,
                            UserBooksRepository userBooksRepository, EmailSenderService emailSenderService, Random random) {
        this.bookService = bookService;
        this.userService = userService;
        this.userBooksRepository = userBooksRepository;
        this.emailSenderService = emailSenderService;
        this.random = random;
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
    public Integer userReturnsBook(String token){
        var userBook = userBooksRepository.findById(token).orElseThrow(UserBookTokenNotFoundException::new);
        userBook.setReturnDate(LocalDate.now());
        bookService.returnBook(userBook.getBook());

        int days = Math.toIntExact(ChronoUnit.DAYS.between(LocalDate.now(), userBook.getExpireDate()));         //each day of late return is 1zl
        userBooksRepository.save(userBook);
        return Math.min(days, 0);
    }

    public void userBorrowsBook(String token){
        var userBook = userBooksRepository.findById(token).orElseThrow(UserBookTokenNotFoundException::new);
        userBook.setBorrowDate(LocalDate.now());
        userBook.setExpireDate(LocalDate.now().plusDays(60));
        userBooksRepository.save(userBook);
    }
    @Transactional
    public void userReservesBook(Integer bookId, String username) {
        var user = (User) userService.loadUserByUsername(username);
        var book = bookService.reserveBook(bookId);
        var userBook = UserBooks.builder()
                .id( 100000 + random.nextInt(900000))
                .book(book)
                .user(user)
                .reservedDate(LocalDate.now())
                .expireDate(LocalDate.now().plusDays(2))
                .build();
        userBooksRepository.save(userBook);
        emailSenderService.sendMail("RESERVED", userBook.getId().toString(),username);
    }
}
