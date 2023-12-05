package com.biblioteka.Library.Exceptions.NotFoundException;


import com.biblioteka.Library.Model.Book;
public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(){
        super("Author not found");
    }
    public AuthorNotFoundException(Book book){
        super("Author for book "+ book.getTitle() +" not found");
    }
    public AuthorNotFoundException(Integer id){
        super("Author with id="+id+" not found");
    }
}
