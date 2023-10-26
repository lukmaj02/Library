package com.biblioteka.Library.ExceptionsHandler;

import com.biblioteka.Library.Exceptions.NotFoundException.AuthorNotFoundException;
import com.biblioteka.Library.Exceptions.NotFoundException.BookNotFoundException;
import com.biblioteka.Library.Exceptions.NotFoundException.ConfirmationTokenNotFound;
import com.biblioteka.Library.Exceptions.NotFoundException.EmployeeNotFoundException;
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
    public String confirmationTokenNotFound(ConfirmationTokenNotFound exception){return exception.getMessage();}
}
