package com.Messaging_System.application.dto.output.exceptions;

import java.time.LocalDateTime;

public record DTO_ExUnauthorized(
        String timestamp,
        Integer code,
        String error,
        String message
) {
}
