package com.biblioteka.Library.Exceptions;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException(){
        super("Invalid password");
    }
}
