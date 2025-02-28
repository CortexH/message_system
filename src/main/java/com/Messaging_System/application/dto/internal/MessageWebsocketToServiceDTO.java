package com.Messaging_System.application.dto.internal;

import com.Messaging_System.domain.enums.WebsocketMessageResponseType;
import com.Messaging_System.domain.model.UserModel;

import java.util.List;

public record MessageWebsocketToServiceDTO(

        String content,
        WebsocketMessageResponseType type,
        UserModel userMessageReceiver,
        UserModel userMessageSender,

        Long messageId,
        List<Long> messageIds

) {
}
