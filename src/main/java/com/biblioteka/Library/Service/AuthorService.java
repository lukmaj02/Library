package com.biblioteka.Library.Service;

import com.biblioteka.Library.Entity.Author;
import com.biblioteka.Library.Exceptions.ExistingException.AuthorExistingBooksException;
import com.biblioteka.Library.Exceptions.ExistingException.AuthorExistingException;
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
    public Author getAuthorByFirstNameAndLastName(String firstName, String lastName){
        return authorRepository.findByFirstNameAndLastName(firstName,lastName).orElseThrow(AuthorNotFoundException::new);
    }

    public Author getAuthorIfNotExistsAdd(Author author){
        try{
            author = getAuthorByFirstNameAndLastName(author.getFirstName(), author.getLastName());
        }
        catch (AuthorNotFoundException authorNotFoundException){
            authorRepository.save(author);
        }
        return author;
    }

    public void changeById(Integer id, AuthorDto authorDto) {
//       try{
//           Author changedAuthor = getAuthorById(id);
//           changedAuthor.setFirstName(authorDto.getFirstName());
//           changedAuthor.setLastName(authorDto.getLastName());
//       }
//       catch(AuthorNotFoundException ignored){}
    }

    public void deleteById(Integer id) {
//        if (!author.getBooks().isEmpty()) throw new AuthorExistingBooksException(modelMapper.map(author, AuthorDto.class));
//        authorRepository.delete(author);
//
//        try{
//            var author = getAuthorById(id);
//        }
//        catch(AuthorNotFoundException ignored){}
    }
}
