package com.biblioteka.Library.Exceptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(){
        super("There is not such book");
    }
    public BookNotFoundException(String title){
        super("There is no book " + title);
    }
}
