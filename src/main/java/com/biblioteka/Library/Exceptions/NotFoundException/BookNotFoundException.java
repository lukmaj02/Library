package com.biblioteka.Library.Exceptions.NotFoundException;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(){
        super("There is not such book");
    }

    public BookNotFoundException(Integer id){
        super("There is no book with id="+ id);
    }
    public BookNotFoundException(String title){
        super("There is no book " + title);
    }
}
