package com.Messaging_System.application.dto.output.userDataDTO;

import java.time.LocalDateTime;

public record SingleMessageDTO(
        String timestamp,
        String content,
        String sender_username
) {
}
