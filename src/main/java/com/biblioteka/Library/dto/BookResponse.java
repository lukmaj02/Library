package com.biblioteka.Library.dto;

import com.biblioteka.Library.Entity.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {
    private String ISBN;
    private String title;
    private Integer publicationDate;
    private String authorFirstName;
    private String authorLastName;
}
