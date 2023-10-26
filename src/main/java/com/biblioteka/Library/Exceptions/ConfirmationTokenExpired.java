package com.biblioteka.Library.Exceptions;

public class ConfirmationTokenExpired extends RuntimeException{
    public ConfirmationTokenExpired(){
        super("Confirmation token expired, try again");
    }
}
