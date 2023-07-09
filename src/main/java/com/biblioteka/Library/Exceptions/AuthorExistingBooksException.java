package com.biblioteka.Library.Exceptions;
import com.biblioteka.Library.Entity.Author;
import com.biblioteka.Library.dto.AuthorResponse;

public class AuthorExistingBooksException extends RuntimeException{
    public AuthorExistingBooksException(AuthorResponse authorResponse){
        super("Cannot delete " + authorResponse.getFirstName() +" "+ authorResponse.getLastName() + ", existing books");
    }
}
