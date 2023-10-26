package com.biblioteka.Library.Exceptions.ExistingException;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(){
        super("Email already exists");
    }
}
