package com.biblioteka.Library.Service;

import com.biblioteka.Library.Entity.Book;
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
    private final ModelMapper modelMapper;
    @Autowired
    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    public List<BookResponse> getBooks() {
        List<Book> books = bookRepository.findAll();
        return Arrays.asList(modelMapper.map(books, BookResponse[].class));
    }

    public BookResponse getBook(String id) {
        return null;
    }

    public void addBook(BookRequest requestBody) {
        //TODO
    }

    public void changeBook(String id, BookRequest requestBody) {
        //TODO
    }

    public void deleteBook(String id) {
    }
}
