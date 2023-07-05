package com.biblioteka.Library.Service;

import com.biblioteka.Library.Entity.Author;
import com.biblioteka.Library.Exceptions.AuthorExistsException;
import com.biblioteka.Library.Exceptions.AuthorNotFoundException;
import com.biblioteka.Library.Repository.AuthorRepository;
import com.biblioteka.Library.dto.AuthorRequest;
import com.biblioteka.Library.dto.AuthorResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AuthorService {

    private final ModelMapper modelMapper;
    private final AuthorRepository authorRepository;
    @Autowired
    public AuthorService(ModelMapper modelMapper, AuthorRepository authorRepository) {
        this.modelMapper = modelMapper;
        this.authorRepository = authorRepository;
    }

    public List<AuthorResponse> getAllAuthors() {
        return Arrays.asList(modelMapper.map(authorRepository.findAll(), AuthorResponse[].class));
    }

    public AuthorResponse getAuthorById(Integer id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        return modelMapper.map(author, AuthorResponse.class);
    }

    public void addAuthor(AuthorRequest authorRequest){
        if(authorRepository.existsByFirstNameAndLastName(authorRequest.getFirstName(),authorRequest.getLastName())){
            throw new AuthorExistsException(authorRequest.getFirstName(),authorRequest.getLastName());
        }
    }
}
