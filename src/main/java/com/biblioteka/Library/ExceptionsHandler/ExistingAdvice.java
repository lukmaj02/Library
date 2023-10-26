package com.biblioteka.Library.ExceptionsHandler;

import com.biblioteka.Library.Exceptions.ExistingException.AuthorExistingBooksException;
import com.biblioteka.Library.Exceptions.ExistingException.AuthorExistingException;
import com.biblioteka.Library.Exceptions.ExistingException.BookExistingException;
import com.biblioteka.Library.Exceptions.ExistingException.EmailAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ExistingAdvice {
    @ExceptionHandler(BookExistingException.class)
    public String bookExistsException(BookExistingException exception){
        return exception.getMessage();
    }
    @ExceptionHandler(AuthorExistingException.class)
    public String authorExistsException(AuthorExistingException exception){
        return exception.getMessage();
    }
    @ExceptionHandler(AuthorExistingBooksException.class)
    public String authorExistingBooksException(AuthorExistingBooksException exception){
        return exception.getMessage();
    }
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public String emailExistingUser(EmailAlreadyExistsException exception){
        return exception.getMessage();
    }
}
