package com.biblioteka.Library.Service;

import com.biblioteka.Library.Entity.Author;
import com.biblioteka.Library.Entity.Book;
import com.biblioteka.Library.Entity.User;
import com.biblioteka.Library.Exceptions.ExistingException.BookExistingException;
import com.biblioteka.Library.Exceptions.BookForbiddenToBorrowException;
import com.biblioteka.Library.Exceptions.NotFoundException.AuthorNotFoundException;
import com.biblioteka.Library.Exceptions.NotFoundException.BookNotFoundException;
import com.biblioteka.Library.Repository.AuthorRepository;
import com.biblioteka.Library.Repository.BookRepository;
import com.biblioteka.Library.dto.AuthorDto;
import com.biblioteka.Library.dto.BookDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    @Autowired
    public BookService(BookRepository bookRepository,
                       AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book getBookByTitle(String title){
        return bookRepository.findByTitle(title).orElseThrow(() -> new BookNotFoundException(title));
    }

    public Book borrowBookById(Integer id){
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        if(book.getQuantity()<=0) throw new BookForbiddenToBorrowException();
        book.setQuantity(book.getQuantity()-1);
        bookRepository.save(book);
        return book;
    }

    @Transactional
    public void addBook(BookDto bookDto) {
        if(bookRepository.existsByIsbn(bookDto.getIsbn())){
            throw new BookExistingException(bookDto.getIsbn());
        }
        var book = Book.builder()
                .title(bookDto.getTitle())
                .isbn(bookDto.getIsbn())
                .quantity(bookDto.getQuantity())
                .publicationDate(bookDto.getPublicationDate())
                .build();

        Author author;
        try{
            author = authorService.getAuthorByAuthorDto(bookDto.getAuthor());
        }
        catch (AuthorNotFoundException authorNotFoundException){
            author =Author.builder()
                    .firstName(bookDto.getAuthor().getFirstName())
                    .lastName(bookDto.getAuthor().getLastName())
                    .build();
            authorService.addAuthor(author);
        }
        book.setAuthor(author);
        bookRepository.save(book);
    }
    public void changeBookQuantity(Integer id, BookDto bookDto) {
        Book book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        book.setQuantity(bookDto.getQuantity());
        bookRepository.save(book);
    }

    public void deleteBook(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        var author = book.getAuthor();
        bookRepository.delete(book);
    }
}
