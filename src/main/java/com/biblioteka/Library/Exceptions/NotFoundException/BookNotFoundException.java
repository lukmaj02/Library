package com.biblioteka.Library.Exceptions.NotFoundException;

import com.biblioteka.Library.Entity.User;

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
    public BookNotFoundException(User user){
        super("Dear " +user.getName() +", you dont have any books yet");
    }
}
