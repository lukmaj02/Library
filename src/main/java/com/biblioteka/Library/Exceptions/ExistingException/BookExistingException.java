package com.biblioteka.Library.Exceptions.ExistingException;

public class BookExistingException extends RuntimeException{
    public BookExistingException(){
        super("Books already exists");
    }
    public BookExistingException(String ISBN){
        super("Book with ISBN=" + ISBN +" already exists!");
    }
}
