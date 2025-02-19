package com.Messaging_System.application.dto.output;

import java.time.LocalDateTime;

public record AuthenticatedDTO(
        LocalDateTime timeStamp,
        Integer code,
        String token
) {
}
