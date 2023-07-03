package com.biblioteka.Library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private String ISBN;
    private String title;
    private String authorName;
    private String authorLastName;
    private Integer publicationDate;
    private Integer quantity;
    private Double price;
    private String language;
}
