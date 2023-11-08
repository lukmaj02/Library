package com.biblioteka.Library.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AuthorDto {
    private String firstName;
    private String lastName;
}
