package com.Messaging_System.application.port;

import com.Messaging_System.domain.model.MessageModel;
import com.Messaging_System.domain.model.UserModel;
import com.Messaging_System.infrastructure.entity.MessageEntity;

import java.util.List;

public interface MessageRepositoryPort {
    MessageModel createMessage(MessageModel messageModel);
    void editMessage(MessageModel messageModel, String content);

    List<MessageModel> getLast50UserMessagesFromUserAndFriend(UserModel user, UserModel friend, Integer lastIndex);
}
