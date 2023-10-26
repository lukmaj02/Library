package com.biblioteka.Library.Exceptions.NotFoundException;

public class ConfirmationTokenNotFound extends  RuntimeException{
    public ConfirmationTokenNotFound(){
        super("Token not Found");
    }
}
