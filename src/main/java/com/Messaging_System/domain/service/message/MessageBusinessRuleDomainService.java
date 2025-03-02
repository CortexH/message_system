package com.Messaging_System.domain.service.message;

import com.Messaging_System.adapter.exception.CustomBadRequestException;
import com.Messaging_System.domain.enums.MessageState;
import com.Messaging_System.domain.model.MessageModel;
import com.Messaging_System.domain.model.UserModel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MessageBusinessRuleDomainService {

    public List<MessageModel> getReadableMessagesFromMessageList(List<MessageModel> list, UserModel reader){
        if(list.isEmpty()) throw new CustomBadRequestException("You cannot read any of these messages!");

        List<MessageModel> allMessages = list.stream()
                .filter(i -> (i.getReceiver().getUuid().equals(reader.getUuid())) && (i.getMessageState() != MessageState.READ))
                .toList();

        if(allMessages.isEmpty()) throw new CustomBadRequestException("You cannot read any of these messages!");

        return allMessages;
    }

}
