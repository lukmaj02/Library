package com.biblioteka.Library.Controller;

import com.biblioteka.Library.Service.UserService;
import com.biblioteka.Library.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/profile")
@PreAuthorize("hasRole('USER')")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }
    @GetMapping("/books")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Collection<BookDto> getUserBooks (@CurrentSecurityContext (expression = "authentication.name") String username){
        return userService.getUserBooks(username);
    }

    @GetMapping("/books/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BookDto getUserBookWithId(@PathVariable Integer id){
        userService.getUserBookWithId(id);
        return null;
    }

    @GetMapping("/book/borrow/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void borrowBook(@CurrentSecurityContext (expression = "authentication.name") String username,@PathVariable Integer id){
        userService.borrowBook(id,username);
    }
}
