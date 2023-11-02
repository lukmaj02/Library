package com.biblioteka.Library.dto.Mapper;

import com.biblioteka.Library.Entity.Book;
import com.biblioteka.Library.Entity.UserBooks;
import com.biblioteka.Library.dto.AuthorDto;
import com.biblioteka.Library.dto.BookDto;

public class BookMapper {
    public static BookDto map(UserBooks book){
        return BookDto.builder()
                .isbn(book.getBook().getIsbn())
                .title(book.getBook().getTitle())
                .author(AuthorDto.builder()
                        .firstName(book.getBook().getAuthor().getFirstName())
                        .lastName(book.getBook().getAuthor().getLastName())
                        .build())
                .publicationDate(book.getBook().getPublicationDate())
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
}
