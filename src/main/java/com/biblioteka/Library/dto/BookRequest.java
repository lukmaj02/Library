package com.biblioteka.Library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {
    private String ISBN;
    private String title;
    private Integer publicationDate;
    private String authorFirstName;
    private String authorLastName;
}
