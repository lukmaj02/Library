package com.biblioteka.Library.Service;

import com.biblioteka.Library.Entity.Author;
import com.biblioteka.Library.Exceptions.AuthorExistsException;
import com.biblioteka.Library.Exceptions.AuthorNotFoundException;
import com.biblioteka.Library.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Integer id) {
        return authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
    }

    public void addAuthor(Author author){
        authorRepository.save(author);
    }

    public boolean existsAuthor(Author author){
        return authorRepository.existsByFirstNameAndLastName(author.getFirstName(), author.getLastName());
    }
}
