package com.biblioteka.Library.Service;

import com.biblioteka.Library.Model.Book;
import com.biblioteka.Library.Repository.BookRepository;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class BookServiceTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorService authorService;

    private BookService underTest;

    @BeforeEach
    void setUp() {
        underTest = new BookService(bookRepository, authorService);
    }

    @Test
    void getBooks() {
        //given
        Book book = new Book();
    }

    @Test
    void getBookById() {
    }
}