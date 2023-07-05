package com.biblioteka.Library.Exceptions;

public class AuthorExistsException extends RuntimeException {
    public AuthorExistsException(String firstName, String lastName) {
        super("Author "+firstName +" " + lastName + " already exists!");
    }
}
