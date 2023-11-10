package com.biblioteka.Library.DTO.Mapper;

import com.biblioteka.Library.Model.Author;
import com.biblioteka.Library.Model.Book;
import com.biblioteka.Library.Model.BookTypes;
import com.biblioteka.Library.Model.UserBooks;
import com.biblioteka.Library.DTO.AuthorDto;
import com.biblioteka.Library.DTO.BookDto;
import com.biblioteka.Library.DTO.UserBookDto;

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
                .type(book.getBook().getType().toString())
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
                .type(book.getType().toString())
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
                .type(BookTypes.valueOf(bookDto.getType()))
                .build();
    }
}
