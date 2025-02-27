package com.Messaging_System.application.service;

import com.Messaging_System.adapter.exception.CustomBadRequestException;
import com.Messaging_System.application.dto.input.WebsocketMessageDTO;
import com.Messaging_System.application.dto.input.WebsocketRequestDTO;
import com.Messaging_System.application.event.sentEvent.UserMessageEvent;
import com.Messaging_System.application.port.MessageRepositoryPort;
import com.Messaging_System.domain.enums.MessageState;
import com.Messaging_System.domain.model.MessageModel;
import com.Messaging_System.domain.model.UserModel;
import com.Messaging_System.domain.service.userFriends.UserFriendsValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepositoryPort repository;
    private final UserService userService;
    private final UserFriendsValidationService friendsValidationService;
    private final ApplicationEventPublisher eventPublisher;

    public void handleUserMessage(
            UserModel user, WebSocketSession session,
            WebsocketRequestDTO receivedMessage
    ){
        WebsocketMessageDTO message = receivedMessage.message();

        switch (message.getType()){
            case SEND -> sendUserMessage(user, message);
            case DELETE -> deleteUserMessage(user, message);
            case READ -> readUserMessages(user, message);
            case MARK_AS_NOT_READ -> markUserMessagesAsNotRead(user, message);
            default -> {}
        }

        eventPublisher.publishEvent(new UserMessageEvent(this, user, message));
    }

    public void sendUserMessage(UserModel sender, WebsocketMessageDTO message){

        UserModel target = userService.findUserByFullUsername(message.getReceiverName());
        if(friendsValidationService.returnTrueIfTargetIsSameAsUser(sender, target)) throw new CustomBadRequestException("You cannot message yourself yet");
        if(!friendsValidationService.validateIfUserCanSendMessageToReceiver(sender, target)) throw new CustomBadRequestException("You cannot message this user");

        MessageModel messageModel = MessageModel.builder()
                .messageState(MessageState.DELIVERED)
                .content(message.getContent())
                .receiver(target)
                .timestamp(LocalDateTime.now())
                .sender(sender)
                .build();

        repository.createMessage(messageModel);
    }

    public void deleteUserMessage(UserModel sender, WebsocketMessageDTO message){

        repository.deleteMessageById(sender, message.getMessage_id().intValue());
    }

    public void readUserMessages(UserModel sender, WebsocketMessageDTO message){

    }

    public void markUserMessagesAsNotRead(UserModel sender, WebsocketMessageDTO message){

    }

    public List<MessageModel> getAllUserMessagesInARangePlus50(UserModel user, UserModel friend, Integer index){
        return repository.getLast50UserMessagesFromUserAndFriend(user, friend, index);
    }


}
