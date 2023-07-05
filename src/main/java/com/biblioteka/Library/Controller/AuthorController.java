package com.biblioteka.Library.Controller;

import com.biblioteka.Library.Service.AuthorService;
import com.biblioteka.Library.dto.AuthorRequest;
import com.biblioteka.Library.dto.AuthorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController implements IController<AuthorRequest, AuthorResponse, Integer> {

    private final AuthorService authorService;
    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public List<AuthorResponse> getAll() {
        return authorService.getAllAuthors();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public AuthorResponse getById(@PathVariable Integer id) {
        return authorService.getAuthorById(id);
    }

    @Override
    @PutMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(AuthorRequest authorRequest) {
        authorService.addAuthor(authorRequest);
    }

    @Override
    public void changeById(Integer integer, AuthorRequest requestBody) {

    }

    @Override
    public void deleteById(Integer integer) {

    }
}
