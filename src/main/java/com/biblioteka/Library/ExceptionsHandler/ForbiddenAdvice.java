package com.biblioteka.Library.ExceptionsHandler;

import com.biblioteka.Library.Exceptions.BookForbiddenToBorrowException;
import com.biblioteka.Library.Exceptions.InvalidPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenAdvice {
    @ExceptionHandler
    public String bookForbiddenToBorrow(BookForbiddenToBorrowException exception){
        return exception.getMessage();
    }
    @ExceptionHandler
    public String invalidPassword(InvalidPasswordException exception) {
        return exception.getMessage();
    }
}
