package com.Messaging_System.application.port;

import com.Messaging_System.domain.model.MessageModel;
import com.Messaging_System.infrastructure.entity.MessageEntity;

public interface MessageRepositoryPort {
    MessageModel createMessage(MessageModel messageModel);
    void editMessage(MessageModel messageModel, String content);
}
