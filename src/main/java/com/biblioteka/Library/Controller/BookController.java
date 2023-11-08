package com.biblioteka.Library.Controller;

import com.biblioteka.Library.Service.BookService;
import com.biblioteka.Library.DTO.BookDto;
import com.biblioteka.Library.DTO.Mapper.BookMapper;
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
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Set<BookDto> getAll() {
        return bookService.getBooks()
                .stream()
                .map(BookMapper::map)
                .collect(Collectors.toSet());
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public BookDto getById(@PathVariable Integer id) {
        return BookMapper.map(bookService.getBookById(id));
    }



}
