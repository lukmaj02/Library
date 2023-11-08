package com.biblioteka.Library.DTO;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserBookDto extends BookDto{
    private final LocalDateTime borrowDate;
    private final LocalDateTime returnDate;
    private final LocalDateTime expireDate;
}
