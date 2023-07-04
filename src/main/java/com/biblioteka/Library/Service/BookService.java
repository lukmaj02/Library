package com.biblioteka.Library.Service;

import com.biblioteka.Library.Entity.Author;
import com.biblioteka.Library.Entity.Book;
import com.biblioteka.Library.Exceptions.AuthorNotFoundException;
import com.biblioteka.Library.Exceptions.BookNotFoundException;
import com.biblioteka.Library.Repository.AuthorRepository;
import com.biblioteka.Library.Repository.BookRepository;
import com.biblioteka.Library.dto.BookRequest;
import com.biblioteka.Library.dto.BookResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public BookService(BookRepository bookRepository, ModelMapper modelMapper, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.authorRepository = authorRepository;
    }

    public List<BookResponse> getBooks() {
        List<Book> books = bookRepository.findAll();
        return Arrays.asList(modelMapper.map(books, BookResponse[].class));
    }

    public BookResponse getBook(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("for now"));//todo
        Author author = authorRepository.findById(book.getAuthorId()).orElseThrow(() -> new AuthorNotFoundException(book.getTitle()));
        return BookResponse.builder()
                .ISBN(book.getISBN())
                .title(book.getTitle())
                .publicationDate(book.getPublicationDate())
                .authorFirstName(author.getFirstName())
                .authorLastName(author.getLastname())
                .build();
    }

    public void addBook(BookRequest requestBody) {
        //TODO
    }

    public void changeBook(Integer id, BookRequest requestBody) {
        //TODO
    }

    public void deleteBook(Integer id) {
    }
}
