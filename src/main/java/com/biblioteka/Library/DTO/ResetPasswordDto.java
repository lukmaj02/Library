package com.biblioteka.Library.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResetPasswordDto {
    String password1;
    String password2;
}
