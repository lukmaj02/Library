package com.biblioteka.Library.Controller;

import com.biblioteka.Library.Service.UserBooksService;
import com.biblioteka.Library.dto.BookDto;
import com.biblioteka.Library.dto.Mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/profile")
@PreAuthorize("hasRole('USER')")
public class UserController {
    private final UserBooksService userBooksService;
    @Autowired
    public UserController(UserBooksService userBooksService) {
        this.userBooksService = userBooksService;
    }


    @GetMapping("/books")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Collection<BookDto> getUserBooks (@CurrentSecurityContext (expression = "authentication.name") String username){
        return userBooksService.getAllUserBooks(username)
                .stream()
                .map(BookMapper::map)
                .collect(Collectors.toSet());
    }

    @GetMapping("/books/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BookDto getUserBookWithId(@CurrentSecurityContext (expression = "authentication.name") String username,
                                     @PathVariable Integer id){
        return BookMapper.map(userBooksService.getUserBookWithId(id,username));
    }

    @GetMapping("/book/borrow/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void borrowBook(@CurrentSecurityContext (expression = "authentication.name") String username,
                           @PathVariable Integer id){
        userBooksService.userBorrowsBook(id,username);
    }

//    @PutMapping("/password")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public changePassword(@RequestBody String password)
}
