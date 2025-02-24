package com.Messaging_System.application.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRegisterDTO(

        @NotBlank(message = "'name' cannot be blank.")
        String name,

        @NotBlank(message = "'password' cannot be blank.")
        String password,

        @NotBlank(message = "'email' cannot be blank.")
        @Email(message = "'email' need to be formated as email.")
        String email,

        @NotBlank(message = "'tag' cannot be blank.")
        String tag
) {
}
