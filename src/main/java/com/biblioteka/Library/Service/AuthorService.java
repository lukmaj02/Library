package com.biblioteka.Library.Service;

import com.biblioteka.Library.Entity.Author;
import com.biblioteka.Library.Exceptions.ExistingException.AuthorExistingException;
import com.biblioteka.Library.Exceptions.ExistingException.AuthorExistingBooksException;
import com.biblioteka.Library.Exceptions.NotFoundException.AuthorNotFoundException;
import com.biblioteka.Library.Repository.AuthorRepository;
import com.biblioteka.Library.dto.AuthorDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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

    public Author getAuthorByAuthorDto(AuthorDto authorDto){
        return authorRepository.findByFirstNameAndLastName(authorDto.getFirstName(), authorDto.getLastName()).orElseThrow(AuthorNotFoundException::new);
    }

    public void addAuthor(Author author){
        authorRepository.save(author);
    }

    public void changeById(Integer id, AuthorDto authorDto) {
       Author changedAuthor = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
       changedAuthor.setFirstName(authorDto.getFirstName());
       changedAuthor.setLastName(authorDto.getLastName());
    }

    public void deleteById(Integer id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        if (!author.getBooks().isEmpty()) throw new AuthorExistingBooksException(modelMapper.map(author, AuthorDto.class));
        authorRepository.delete(author);
    }

    public boolean existsAuthor(AuthorDto authorDto){
        return authorRepository.existsByFirstNameAndLastName(authorDto.getFirstName(), authorDto.getLastName());
    }
}
