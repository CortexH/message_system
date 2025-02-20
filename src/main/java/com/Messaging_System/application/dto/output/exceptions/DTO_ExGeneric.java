package com.Messaging_System.application.dto.output.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DTO_ExGeneric(
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        LocalDateTime timestamp,
        Integer code,
        String error,
        String message
) {
}
