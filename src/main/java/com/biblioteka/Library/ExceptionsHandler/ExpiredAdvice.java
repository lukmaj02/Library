package com.biblioteka.Library.ExceptionsHandler;

import com.biblioteka.Library.Exceptions.ConfirmationTokenExpired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ExpiredAdvice {
    @ExceptionHandler(ConfirmationTokenExpired.class)
    public String confirmationTokenExpired(ConfirmationTokenExpired exception){
        return exception.getMessage();
    }

}
