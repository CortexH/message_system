package com.Messaging_System.domain.service.message;

import com.Messaging_System.application.port.MessageRepositoryPort;
import com.Messaging_System.domain.model.MessageModel;
import com.Messaging_System.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageValidationService {

    private final MessageRepositoryPort repository;

    public Boolean returnTrueIfMessageIsOfSpecifiedUser(UserModel user, MessageModel message){
        return (message.getSender().getEmail().equals(user.getEmail()));
    }

}
