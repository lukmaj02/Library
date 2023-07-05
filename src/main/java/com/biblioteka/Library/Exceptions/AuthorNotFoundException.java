package com.biblioteka.Library.Exceptions;

import com.biblioteka.Library.Entity.Book;
import jakarta.persistence.criteria.CriteriaBuilder;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(Book book){
        super("Author for book "+ book.getTitle() +" not found");
    }
    public AuthorNotFoundException(Integer id){
        super("Author with id="+id+" not found");
    }

    public AuthorNotFoundException(String firstName, String lastName){
        super("Author "+firstName + " " +lastName + " not found ");
    }
}
