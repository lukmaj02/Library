package com.biblioteka.Library.Exceptions.NotFoundException;

public class UserBookTokenNotFoundException extends RuntimeException{
    public UserBookTokenNotFoundException(){
        super("Token not found");
    }
}
