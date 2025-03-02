package com.Messaging_System.application.service.websocket;

import com.Messaging_System.application.dto.output.GenericSuccessfullDTO;
import com.Messaging_System.application.dto.output.websocket.WebsocketFriendRequestNotify;
import com.Messaging_System.application.dto.output.websocket.WebsocketFriendRequestResponseDTO;
import com.Messaging_System.application.dto.output.websocket.WebsocketMessageNotifyDTO;
import com.Messaging_System.application.service.UserService;
import com.Messaging_System.domain.enums.FriendRequestResponseType;
import com.Messaging_System.domain.enums.WebsocketMessageResponseType;
import com.Messaging_System.domain.enums.WebsocketResponseType;
import com.Messaging_System.domain.model.MessageModel;
import com.Messaging_System.domain.model.UserFriendsModel;
import com.Messaging_System.domain.model.UserModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WebsocketNotificationService {

    private final WebsocketSessionService sessionService;
    private final ObjectMapper objectMapper;
    private final UserService userService;

    public void friendRequestNotification(UserFriendsModel userFriendsModel) throws IOException {
        UserModel user = userFriendsModel.getUser();
        UserModel friend = userFriendsModel.getFriend();

        WebSocketSession friendSession = sessionService.getSessionByUser(friend);
        WebSocketSession userSession = sessionService.getSessionByUser(user);

        if(friendSession != null){
            friendSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(
                    new WebsocketFriendRequestNotify(
                            WebsocketResponseType.FRIEND_REQUEST,
                            FriendRequestResponseType.SENT,
                            userService.formatUserAsDTO(user)
                    )
            )));
        }

        if(userSession != null){
            userSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(
                    new GenericSuccessfullDTO(
                            LocalDateTime.now().toString(),
                            200,
                            "Successfully sent friend request!"
                    )
            )));
        }

    }

    public void sendUserMessage(MessageModel message, UserModel sender) throws IOException {
        UserModel target = message.getReceiver();

        WebSocketSession friendSession = sessionService.getSessionByUser(target);
        WebSocketSession userSession = sessionService.getSessionByUser(sender);

        if(friendSession == null){
            return;
        }

        friendSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(
                WebsocketMessageNotifyDTO.builder()
                        .type(WebsocketResponseType.MESSAGE)
                        .messageResponseType(WebsocketMessageResponseType.SEND)
                        .content(message.getContent())
                        .userName(target.getName() + "#" + target.getTag())
                        .build()
                )
        ));

        userSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(
                new GenericSuccessfullDTO(
                        LocalDateTime.now().toString(),
                        200,
                        "successfully send message to target"
                )
        )));
    }

    public void deleteUserMessageNotification(MessageModel message, UserModel sender) throws IOException {
        UserModel target = message.getReceiver();
        WebSocketSession friendSession = sessionService.getSessionByUser(target);
        WebSocketSession senderSession = sessionService.getSessionByUser(sender);

        if(friendSession != null){
            friendSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(
                    WebsocketMessageNotifyDTO.builder()
                            .type(WebsocketResponseType.MESSAGE)
                            .messageResponseType(WebsocketMessageResponseType.DELETE)
                            .userName(target.getName() + "#" + target.getTag())
                            .message_id(message.getId())
                            .build()
            )));
        }

        if(senderSession != null){
            senderSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(
                    new GenericSuccessfullDTO(
                            LocalDateTime.now().toString(),
                            200,
                            "successfully deleted message!"
                    )
            )
            ));
        }

    }

    public void readUserMessageNotification(List<MessageModel> allMessages) throws IOException {
        MessageModel example_message = allMessages.getFirst();
        UserModel messagesSender = example_message.getSender();
        UserModel messagesReceiver = example_message.getReceiver();

        WebSocketSession senderSession = sessionService.getSessionByUser(messagesSender);
        WebSocketSession receiverSession = sessionService.getSessionByUser(messagesReceiver);

        if(senderSession != null){
            senderSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(
                    WebsocketMessageNotifyDTO.builder()
                            .type(WebsocketResponseType.MESSAGE)
                            .messageResponseType(WebsocketMessageResponseType.READ)
                            .userName(messagesReceiver.getName() + "#" + messagesReceiver.getTag())
                            .messages_ids(allMessages.stream().map(MessageModel::getId).toList())
                            .build()
            )));
        }

        if(receiverSession != null){
            receiverSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(
                    new GenericSuccessfullDTO(
                            LocalDateTime.now().toString(),
                            200,
                            "successfully read all messages"
                    )
            )
            ));
        }

    }

    public void markAsNotReadUserMessageNotification(MessageModel message){
        UserModel target = message.getReceiver();
        WebSocketSession session = sessionService.getSessionByUser(target);
    }

    public void notifyToUserAndFriendThatFriendRequestHasAcceptedOrDenied(UserModel user, UserModel friend, FriendRequestResponseType state) throws IOException {
        WebSocketSession userSession = sessionService.getSessionByUser(user);
        WebSocketSession friendSession = sessionService.getSessionByUser(friend);

        friendSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(
                new GenericSuccessfullDTO(
                        LocalDateTime.now().toString(),
                        200,
                        "Successfully accepted user request"
                )
        )));

        userSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(
                new WebsocketFriendRequestResponseDTO(
                        WebsocketResponseType.FRIEND_REQUEST,
                        state,
                        user.getName() + "#" + user.getTag(),
                        null
                )
        )));

    }
}
