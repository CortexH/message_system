package com.Messaging_System.application.dto.output.exceptions;

import java.time.LocalDateTime;
import java.util.Map;

public record DTO_ExValidation(
        LocalDateTime timestamp,
        Integer code,
        String error,
        Map<String, String> messages
) {
}
