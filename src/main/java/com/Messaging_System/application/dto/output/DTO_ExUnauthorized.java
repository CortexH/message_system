package com.Messaging_System.application.dto.output;

import java.time.LocalDateTime;

public record DTO_ExUnauthorized(
        LocalDateTime timestamp,
        Integer code,
        String error,
        String message
) {
}
