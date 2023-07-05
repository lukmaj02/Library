package com.biblioteka.Library.Exceptions;

public class BookExistsException extends RuntimeException{
    public BookExistsException(String ISBN){
        super("Book with ISBN=" + ISBN +" already exists!");
    }
}
