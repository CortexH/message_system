package com.Messaging_System.application.port;

import com.Messaging_System.domain.model.MessageModel;
import com.Messaging_System.domain.model.UserModel;

import java.util.List;

public interface MessageRepositoryPort {
    MessageModel findMessageById(Long id);
    MessageModel createMessage(MessageModel messageModel);
    void editMessage(MessageModel messageModel, String content);

    List<MessageModel> getLast50UserMessagesFromUserAndFriend(UserModel user, UserModel friend, Integer lastIndex);

    void deleteMessageById(UserModel user, Long message_id);
    UserModel getMessageSenderByMessageId(Long id);
    UserModel getMessageReceiverByMessageId(Long id);
}
