package com.biblioteka.Library.Exceptions;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(){
        super("Email already exists");
    }
}
