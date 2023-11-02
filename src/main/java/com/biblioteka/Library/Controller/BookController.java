package com.biblioteka.Library.Controller;

import com.biblioteka.Library.Service.BookService;
import com.biblioteka.Library.dto.BookDto;
import com.biblioteka.Library.dto.Mapper.BookMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Set<BookDto> getAll() {
        return bookService.getBooks()
                .stream()
                .map(BookMapper::map)
                .collect(Collectors.toSet());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BookDto getById(@PathVariable Integer id) {
        return BookMapper.map(bookService.getBookById(id));
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    public void add(@RequestBody BookDto bookDto) {
        bookService.addBook(bookDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    public void changeQuantityById(@PathVariable Integer id, @RequestBody BookDto bookDto) {
        bookService.changeBookQuantity(id, bookDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_EMPLOYEE')")
    public void deleteById(@PathVariable Integer id) {bookService.deleteBook(id);
    }
}
