package com.biblioteka.Library.ExceptionsHandler;

import com.biblioteka.Library.Exceptions.NotFoundException.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundAdvice {
    @ExceptionHandler(EmployeeNotFoundException.class)
    public String employeeNotFoundAdvice(EmployeeNotFoundException exception){
        return exception.getMessage();
    }
    @ExceptionHandler(BookNotFoundException.class)
    public String bookNotFoundAdvice(BookNotFoundException exception){
        return exception.getMessage();
    }
    @ExceptionHandler(AuthorNotFoundException.class)
    public String authorNotFoundAdvice(AuthorNotFoundException exception){
        return exception.getMessage();
    }
    @ExceptionHandler(ConfirmationTokenNotFound.class)
    public String confirmationTokenNotFoundAdvice(ConfirmationTokenNotFound exception){return exception.getMessage();}
    @ExceptionHandler(UserNotFoundException.class)
    public String userNotFoundAdvice(UserNotFoundException exception) {return exception.getMessage();}

    @ExceptionHandler(UserBookTokenNotFoundException.class)
    public String userBookTokenNotFoundAdvice(UserBookTokenNotFoundException exception){
        return exception.getMessage();
    }

}
