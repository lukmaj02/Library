package com.biblioteka.Library.Controller;

import com.biblioteka.Library.Entity.User;
import com.biblioteka.Library.Service.UserBooksService;
import com.biblioteka.Library.Service.UserService;
import com.biblioteka.Library.DTO.BookDto;
import com.biblioteka.Library.DTO.Mapper.BookMapper;
import com.biblioteka.Library.DTO.Mapper.UserMapper;
import com.biblioteka.Library.DTO.UserDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/profile")
@PreAuthorize("hasRole('USER') or hasRole('EMPLOYEE')")
public class UserController {
    private final UserBooksService userBooksService;
    private final UserService userService;
    @Autowired
    public UserController(UserBooksService userBooksService, UserService userService) {
        this.userBooksService = userBooksService;
        this.userService = userService;
    }


    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookDto> getUserBooks (@CurrentSecurityContext (expression = "authentication.name") String username){
        return userBooksService.getAllUserBooks(username)
                .stream()
                .map(BookMapper::map)
                .collect(Collectors.toSet());
    }

    @GetMapping("/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDto getUserBookWithId(@CurrentSecurityContext (expression = "authentication.name") String username,
                                     @PathVariable Integer id){
        return BookMapper.map(userBooksService.getUserBookWithId(id,username));
    }

    @GetMapping("/settings")
    @ResponseStatus(HttpStatus.OK)
    public UserDto viewInformation(@CurrentSecurityContext(expression = "authentication.name") String username){
        return UserMapper.map((User) userService.loadUserByUsername(username));
    }

    @PutMapping("/reserve")
    @ResponseStatus(HttpStatus.OK)
    public void reserveBook(@CurrentSecurityContext(expression = "authentication.name") String username,
                            @PathParam("book_id") Integer book_id){
        userBooksService.userReservesBook(book_id, username);
    }

}
