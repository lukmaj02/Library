package com.biblioteka.Library.Exceptions;

public class BookExistingException extends RuntimeException{
    public BookExistingException(String ISBN){
        super("Book with ISBN=" + ISBN +" already exists!");
    }
}
