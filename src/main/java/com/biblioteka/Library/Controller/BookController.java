package com.biblioteka.Library.Controller;

import com.biblioteka.Library.Repository.BookRepository;
import com.biblioteka.Library.Service.BookService;
import com.biblioteka.Library.dto.BookRequest;
import com.biblioteka.Library.dto.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
@RestController
@RequestMapping("/book")
public class BookController implements IController<BookRequest, BookResponse, String>{

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    @GetMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<BookResponse> getAll() {
        return bookService.getBooks();
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BookResponse getById(@PathVariable String id) {
        return bookService.getBook(id);
    }

    @Override
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody BookRequest requestBody) {
        bookService.addBook(requestBody);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changeById(@PathVariable String id, @RequestBody BookRequest requestBody) {
        bookService.changeBook(id, requestBody);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteById(String id) {
        bookService.deleteBook(id);
    }
}
