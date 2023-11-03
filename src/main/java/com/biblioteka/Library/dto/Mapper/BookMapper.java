package com.biblioteka.Library.dto.Mapper;

import com.biblioteka.Library.Entity.Author;
import com.biblioteka.Library.Entity.Book;
import com.biblioteka.Library.Entity.UserBooks;
import com.biblioteka.Library.dto.AuthorDto;
import com.biblioteka.Library.dto.BookDto;
import com.biblioteka.Library.dto.UserBookDto;

public final class BookMapper {
    private BookMapper() {}
    public static BookDto map(UserBooks book){
        return UserBookDto.builder()
                .isbn(book.getBook().getIsbn())
                .title(book.getBook().getTitle())
                .author(AuthorDto.builder()
                        .firstName(book.getBook().getAuthor().getFirstName())
                        .lastName(book.getBook().getAuthor().getLastName())
                        .build())
                .publicationDate(book.getBook().getPublicationDate())
                .borrowDate(book.getBorrowDate())
                .expireDate(book.getExpireDate())
                .returnDate(book.getReturnDate())
                .build();
    }

    public static BookDto map(Book book){
        return BookDto.builder()
                .quantity(book.getQuantity())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(AuthorDto.builder()
                        .firstName(book.getAuthor().getFirstName())
                        .lastName(book.getAuthor().getLastName())
                        .build())
                .publicationDate(book.getPublicationDate())
                .build();
    }

    public static Book map(BookDto bookDto){
        return Book.builder()
                .title(bookDto.getTitle())
                .isbn(bookDto.getIsbn())
                .quantity(bookDto.getQuantity())
                .publicationDate(bookDto.getPublicationDate())
                .author(Author.builder()
                        .firstName(bookDto.getAuthor().getFirstName())
                        .lastName(bookDto.getAuthor().getLastName())
                        .build())
                .build();
    }
}
