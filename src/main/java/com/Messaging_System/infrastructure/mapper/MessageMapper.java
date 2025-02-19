package com.Messaging_System.infrastructure.mapper;

import com.Messaging_System.domain.model.MessageModel;
import com.Messaging_System.infrastructure.entity.MessageEntity;

public class MessageMapper {

    public static MessageEntity toEntity(MessageModel model){
        return MessageEntity.builder()
                .id(model.getId())
                .content(model.getContent())
                .receiver(UserMapper.toEntity(model.getReceiver()))
                .sender(UserMapper.toEntity(model.getSender()))
                .messageState(model.getMessageState())
                .timestamp(model.getTimestamp())
                .build();
    }

    public static MessageModel toModel(MessageEntity entity){
        return MessageModel.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .receiver(UserMapper.toModel(entity.getReceiver()))
                .sender(UserMapper.toModel(entity.getSender()))
                .messageState(entity.getMessageState())
                .timestamp(entity.getTimestamp())
                .build();
    }

}
