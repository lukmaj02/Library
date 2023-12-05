package com.biblioteka.Library.Service;

import com.biblioteka.Library.Model.Book;
import com.biblioteka.Library.Exceptions.ExistingException.BookExistingException;
import com.biblioteka.Library.Exceptions.BookForbiddenToBorrowException;
import com.biblioteka.Library.Exceptions.NotFoundException.BookNotFoundException;
import com.biblioteka.Library.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public void returnBook(Book book){
        book.setQuantity(book.getQuantity()+1);
        bookRepository.save(book);
    }

    public Book reserveBook(Integer bookId){
        var book = bookRepository.findById(bookId).orElseThrow(BookExistingException::new);
        if(book.getQuantity()<=0) throw new BookForbiddenToBorrowException();
        book.setQuantity(book.getQuantity()-1);
        bookRepository.save(book);
        return book;
    }

    @Transactional
    public void addBook(Book book) {
        if(bookRepository.existsByIsbnOrTitle(book.getIsbn(), book.getTitle())) throw new BookExistingException();
        book.setAuthor(authorService.getAuthorIfNotExistsAdd(book.getAuthor()));
        bookRepository.save(book);
    }
    public void changeBookQuantity(Integer bookId, Integer quantity) {
        var book = getBookById(bookId);
        book.setQuantity(quantity);
        bookRepository.save(book);
    }
    public void deleteBook(Integer id) {
        var book = getBookById(id);
        bookRepository.delete(book);
    }
}
