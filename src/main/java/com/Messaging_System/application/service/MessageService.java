package com.Messaging_System.application.service;

import com.Messaging_System.adapter.exception.CustomBadRequestException;
import com.Messaging_System.application.dto.input.WebsocketMessageDTO;
import com.Messaging_System.application.dto.input.WebsocketRequestDTO;
import com.Messaging_System.application.event.sentEvent.UserMessageEvent;
import com.Messaging_System.application.port.MessageRepositoryPort;
import com.Messaging_System.domain.enums.MessageState;
import com.Messaging_System.domain.model.MessageModel;
import com.Messaging_System.domain.model.UserModel;
import com.Messaging_System.domain.service.message.MessageBusinessRuleDomainService;
import com.Messaging_System.domain.service.message.MessageValidationService;
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

    private final MessageValidationService validationService;
    private final MessageRepositoryPort repository;
    private final UserService userService;
    private final UserFriendsValidationService friendsValidationService;
    private final ApplicationEventPublisher eventPublisher;
    private final MessageBusinessRuleDomainService businessRulesService;

    public void handleUserMessage(
            UserModel user,
            WebsocketRequestDTO receivedMessage
    ){

        MessageModel userMessage = null;
        List<MessageModel> allMessages = null;

        WebsocketMessageDTO message = receivedMessage.message();

        switch (message.getType()){
            case SEND -> userMessage = sendUserMessage(user, message);
            case DELETE -> userMessage = deleteUserMessage(user, message);
            case READ -> allMessages = readUserMessages(user, message);
            case MARK_AS_NOT_READ -> markUserMessagesAsNotRead(user, message);
            default -> {}
        }

        eventPublisher.publishEvent(new UserMessageEvent(
                this,
                user,
                userMessage,
                message.getType(),
                allMessages
        ));
    }

    public MessageModel sendUserMessage(UserModel sender, WebsocketMessageDTO message){
        UserModel target = userService.findUserByFullUsername(message.getReceiverName());

        if(friendsValidationService.returnTrueIfTargetIsSameAsUser(sender, target)) throw new CustomBadRequestException("You cannot message yourself yet");
        if(!friendsValidationService.validateIfUserCanSendMessageToReceiver(sender, target)) throw new CustomBadRequestException("You cannot message this user");
        if(!friendsValidationService.validateIfUserIsFriendOfTarget(sender, target)) throw new CustomBadRequestException("You cannot message this user");

        MessageModel messageModel = MessageModel.builder()
                .messageState(MessageState.DELIVERED)
                .content(message.getContent())
                .receiver(target)
                .timestamp(LocalDateTime.now())
                .sender(sender)
                .build();

        return repository.createMessage(messageModel);
    }

    public MessageModel deleteUserMessage(UserModel sender, WebsocketMessageDTO websocketMessageDTO){
        MessageModel message = repository.findMessageById(websocketMessageDTO.getMessage_id());
        if(!validationService.returnTrueIfMessageIsOfSpecifiedUser(sender, message)) throw new CustomBadRequestException("You cannot delete that message");
        repository.deleteMessageById(sender, message.getId());
        return message;
    }

    public List<MessageModel> readUserMessages(UserModel sender, WebsocketMessageDTO message){

        List<MessageModel> allMessages = repository.findAllMessagesInIdList(message.getMessage_ids());
        List<MessageModel> readableMessages = businessRulesService.getReadableMessagesFromMessageList(allMessages, sender);

        if(readableMessages.isEmpty()) throw new CustomBadRequestException("You cannot read any of these messages!");

        repository.readMessagesById(readableMessages.stream().map(MessageModel::getId).toList());
        return readableMessages;
    }

    public void markUserMessagesAsNotRead(UserModel sender, WebsocketMessageDTO message){

    }

    public List<MessageModel> getAllUserMessagesInARangePlus50(UserModel user, UserModel friend, Integer index){
        return repository.getLast50UserMessagesFromUserAndFriend(user, friend, index);
    }

    public UserModel getSenderByMessageId(Long id){
        return repository.getMessageSenderByMessageId(id);
    }

    public UserModel getReceiverByMessageId(Long id){
        return repository.getMessageReceiverByMessageId(id);
    }


}
