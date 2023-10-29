package com.biblioteka.Library.Controller;

import com.biblioteka.Library.Entity.Author;
import com.biblioteka.Library.Service.AuthorService;
import com.biblioteka.Library.dto.AuthorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;
    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @GetMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Author> getAll() {
        return authorService.getAllAuthors();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Author getById(@PathVariable Integer id) {
        return authorService.getAuthorById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changeById(@PathVariable Integer id, @RequestBody AuthorDto authorDto) {
        authorService.changeById(id, authorDto);
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteById(@PathVariable Integer id) {
        authorService.deleteById(id);
    }
}
