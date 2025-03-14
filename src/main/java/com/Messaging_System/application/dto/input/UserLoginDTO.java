package com.Messaging_System.application.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserLoginDTO(
        @NotBlank(message = "'password' cannot be blank.")
        @NotNull(message = "'password' cannot be null.")
        @Size(min = 8, message = "length of password need to be greater than 7.")
        String password,

        @NotBlank(message = "'email' cannot be blank.")
        @NotNull(message = "'email' cannot be null.")
        @Email(message = "'email' need to be formated as email.")
        String email
) {
}
