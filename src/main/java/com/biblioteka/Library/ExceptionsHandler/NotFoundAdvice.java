package com.biblioteka.Library.ExceptionsHandler;

import com.biblioteka.Library.Entity.Book;
import com.biblioteka.Library.Exceptions.AuthorNotFoundException;
import com.biblioteka.Library.Exceptions.BookNotFoundException;
import com.biblioteka.Library.Exceptions.EmployeeNotFoundException;
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
}
