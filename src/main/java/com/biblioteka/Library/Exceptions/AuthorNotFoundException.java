package com.biblioteka.Library.Exceptions;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(String title){
        super("Author for book "+ title +" not found");
    }
}
