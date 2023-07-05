package com.biblioteka.Library.ExceptionsHandler;

import com.biblioteka.Library.Exceptions.AuthorExistsException;
import com.biblioteka.Library.Exceptions.BookExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ExistsAdivce {
    @ExceptionHandler(BookExistsException.class)
    public String bookExistsException(BookExistsException exception){
        return exception.getMessage();
    }
    @ExceptionHandler(AuthorExistsException.class)
    public String authorExistsException(AuthorExistsException exception){
        return exception.getMessage();
    }
}
