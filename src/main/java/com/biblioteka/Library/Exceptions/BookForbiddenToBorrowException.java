package com.biblioteka.Library.Exceptions;

public class BookForbiddenToBorrowException extends RuntimeException{
    public BookForbiddenToBorrowException(){
        super("Cannot borrow this book");
    }
}
