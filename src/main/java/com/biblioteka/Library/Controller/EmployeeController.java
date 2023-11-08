package com.biblioteka.Library.Controller;

import com.biblioteka.Library.DTO.BookDto;
import com.biblioteka.Library.DTO.Mapper.BookMapper;
import com.biblioteka.Library.Service.BookService;
import com.biblioteka.Library.Service.UserBooksService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@PreAuthorize("hasRole('EMPLOYEE')")
public class EmployeeController {
    private final UserBooksService userBooksService;
    private final BookService bookService;
    @Autowired
    public EmployeeController(UserBooksService userBooksService, BookService bookService) {
        this.userBooksService = userBooksService;
        this.bookService = bookService;
    }

    @PutMapping("/borrow")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void borrowBook(@PathParam("token") String token){
        userBooksService.userBorrowsBook(token);
    }

    @PutMapping("/return")
    @ResponseStatus(HttpStatus.OK)
    public Integer returnBook(@PathParam("token") String token){
        return userBooksService.userReturnsBook(token);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void addBook(@RequestBody BookDto bookDto) {
        bookService.addBook(BookMapper.map(bookDto));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changeQuantityById(@RequestParam("id") Integer id, @RequestBody Integer quantity) {
        bookService.changeBookQuantity(id, quantity);
    }
}
