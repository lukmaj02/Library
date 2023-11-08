package com.biblioteka.Library.Exceptions.ExistingException;

import com.biblioteka.Library.DTO.AuthorDto;

public class AuthorExistingException extends RuntimeException {
    public AuthorExistingException(){
        super("Author exists");
    }
    public AuthorExistingException(AuthorDto authorDto) {
        super("Author " + authorDto.getFirstName()+ " "+ authorDto.getLastName() + " already exists!");
    }
}
