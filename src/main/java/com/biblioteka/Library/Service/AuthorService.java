package com.biblioteka.Library.Service;

import com.biblioteka.Library.Entity.Author;
import com.biblioteka.Library.Exceptions.ExistingException.AuthorExistingException;
import com.biblioteka.Library.Exceptions.ExistingException.AuthorExistingBooksException;
import com.biblioteka.Library.Exceptions.NotFoundException.AuthorNotFoundException;
import com.biblioteka.Library.Repository.AuthorRepository;
import com.biblioteka.Library.dto.AuthorResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public AuthorService(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Integer id) {
        return authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
    }

    public void addAuthor(Author author){
        if(existsAuthor(author)) throw new AuthorExistingException(modelMapper.map(author, AuthorResponse.class));
        authorRepository.save(author);
    }

    public void changeById(Integer id, Author author) {
       Author changedAuthor = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
       changedAuthor.setFirstName(author.getFirstName());
       changedAuthor.setLastName(author.getLastName());
    }

    public void deleteById(Integer id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        if (!author.getBooks().isEmpty()) throw new AuthorExistingBooksException(modelMapper.map(author, AuthorResponse.class));
        authorRepository.delete(author);
    }

    public boolean existsAuthor(Author author){
        return authorRepository.existsByFirstNameAndLastName(author.getFirstName(), author.getLastName());
    }
}
