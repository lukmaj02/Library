package com.biblioteka.Library.Exceptions.ExistingException;

import com.biblioteka.Library.dto.AuthorDto;

public class AuthorExistingException extends RuntimeException {
    public AuthorExistingException(AuthorDto authorDto) {
        super("Author " + authorDto.getFirstName()+ " "+ authorDto.getLastName() + " already exists!");
    }
}
