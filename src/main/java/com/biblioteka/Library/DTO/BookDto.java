package com.biblioteka.Library.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String isbn;
    private String title;
    private Integer publicationDate;
    @JsonIgnore
    private Integer quantity;
    private AuthorDto author;
}
