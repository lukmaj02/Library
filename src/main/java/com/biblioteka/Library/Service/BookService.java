package com.biblioteka.Library.Service;

import com.biblioteka.Library.Entity.Author;
import com.biblioteka.Library.Entity.Book;
import com.biblioteka.Library.Entity.User;
import com.biblioteka.Library.Exceptions.ExistingException.AuthorExistingException;
import com.biblioteka.Library.Exceptions.ExistingException.BookExistingException;
import com.biblioteka.Library.Exceptions.BookForbiddenToBorrowException;
import com.biblioteka.Library.Exceptions.NotFoundException.AuthorNotFoundException;
import com.biblioteka.Library.Exceptions.NotFoundException.BookNotFoundException;
import com.biblioteka.Library.Repository.AuthorRepository;
import com.biblioteka.Library.Repository.BookRepository;
import com.biblioteka.Library.dto.AuthorDto;
import com.biblioteka.Library.dto.BookRequest;
import com.biblioteka.Library.dto.BookResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final ModelMapper modelMapper;

    @Autowired
    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository, AuthorService authorService, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    public List<BookResponse> getBooks() {
        List<Book> books = bookRepository.findAll();
        return Arrays.asList(modelMapper.map(books,BookResponse[].class));
    }

    public BookResponse getBook(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        return modelMapper.map(book, BookResponse.class);
    }

    public boolean isPossibleToBorrow(Book book){
        return book.getQuantity() >= 0;
    }

    public Book borrowBookById(Integer id){
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        if(!isPossibleToBorrow(book)) throw new BookForbiddenToBorrowException();
        book.setQuantity(book.getQuantity()-1);
        bookRepository.save(book);
        return book;
    }

    public void addBook(BookRequest bookRequest) {
        if(bookRepository.existsByIsbn(bookRequest.getIsbn())){
            throw new BookExistingException(bookRequest.getIsbn());
        }
        Book book = modelMapper.map(bookRequest, Book.class);
        Author author;

        try{
            author = authorService.getAuthorByAuthorDto(bookRequest.getAuthor());
        }
        catch (AuthorNotFoundException authorNotFoundException){
            author =Author.builder()
                    .firstName(bookRequest.getAuthor().getFirstName())
                    .lastName(bookRequest.getAuthor().getLastName())
                    .build();
            authorService.addAuthor(author);
        }

        book.setAuthor(author);
        bookRepository.save(book);
    }

    public void changeBook(Integer id, BookRequest bookRequest) {
        Book book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    public void deleteBook(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        bookRepository.delete(book);
    }

    public Collection<Book> getUserBooks(User user){
        return bookRepository.findByUsers(user).orElseThrow(() -> new BookNotFoundException(user));
    }
}
