package com.biblioteka.Library.Exceptions;

import com.biblioteka.Library.dto.AuthorResponse;

public class AuthorExistingException extends RuntimeException {
    public AuthorExistingException(AuthorResponse authorResponse) {
        super("Author " + authorResponse.getFirstName()+ " "+ authorResponse.getLastName() + " already exists!");
    }
}
