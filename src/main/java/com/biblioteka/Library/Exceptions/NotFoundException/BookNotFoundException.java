package com.biblioteka.Library.Exceptions.NotFoundException;

import com.biblioteka.Library.Model.Author;
import com.biblioteka.Library.Model.User;

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
    public BookNotFoundException(Author author) {
        super("There is no books written by " + author.getFirstName()+ " " + author.getLastName());
    }
}
