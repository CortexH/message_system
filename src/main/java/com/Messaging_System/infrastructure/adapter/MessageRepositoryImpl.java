package com.Messaging_System.infrastructure.adapter;

import com.Messaging_System.application.port.MessageRepositoryPort;
import com.Messaging_System.domain.model.MessageModel;
import com.Messaging_System.domain.model.UserModel;
import com.Messaging_System.infrastructure.entity.MessageEntity;
import com.Messaging_System.infrastructure.mapper.MessageMapper;
import com.Messaging_System.infrastructure.repository.MessageRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MessageRepositoryImpl implements MessageRepositoryPort {

    private final MessageRepository messageRepository;

    @Override
    public MessageModel createMessage(MessageModel model) {
        MessageEntity entity = MessageMapper.toEntity(model);
        return MessageMapper.toModel(messageRepository.save(entity));
    }

    @Override
    public void editMessage(MessageModel model, String content) {
        model.setContent(content);
        MessageEntity entity = MessageMapper.toEntity(model);
        messageRepository.save(entity);
    }

    @Override
    public List<MessageModel> getLast50UserMessagesFromUserAndFriend(UserModel user, UserModel friend, Integer lastIndex) {
        List<MessageEntity> allMessages = messageRepository.get50LastsMessagesFromUserAndOtherUser(user.getUuid(), friend.getUuid(), lastIndex);
        return allMessages.stream().map(MessageMapper::toModel).toList();
    }
}
