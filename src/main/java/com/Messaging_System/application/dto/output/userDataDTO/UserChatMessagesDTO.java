package com.Messaging_System.application.dto.output.userDataDTO;

import java.util.List;

public record UserChatMessagesDTO(

        List<SingleMessageDTO> messages
) {
}
