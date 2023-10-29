package com.biblioteka.Library.Exceptions.ExistingException;
import com.biblioteka.Library.dto.AuthorDto;

public class AuthorExistingBooksException extends RuntimeException{
    public AuthorExistingBooksException(AuthorDto authorDto){
        super("Cannot delete " + authorDto.getFirstName() +" "+ authorDto.getLastName() + ", existing books");
    }
}
