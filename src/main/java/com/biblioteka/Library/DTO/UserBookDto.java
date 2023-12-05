package com.biblioteka.Library.DTO;

import lombok.*;
import lombok.experimental.SuperBuilder;


import java.time.LocalDate;

@Data
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserBookDto extends BookDto{
    private final LocalDate borrowDate;
    private final LocalDate returnDate;
    private final LocalDate expireDate;
}
