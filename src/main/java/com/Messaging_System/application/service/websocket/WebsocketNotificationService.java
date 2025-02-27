package com.Messaging_System.application.service.websocket;

import com.Messaging_System.application.dto.input.WebsocketMessageDTO;
import com.Messaging_System.application.dto.output.GenericSuccessfullDTO;
import com.Messaging_System.application.dto.output.websocket.WebsocketFriendRequestNotify;
import com.Messaging_System.application.dto.output.websocket.WebsocketFriendRequestResponseDTO;
import com.Messaging_System.application.dto.output.websocket.WebsocketMessageNotifyDTO;
import com.Messaging_System.application.service.UserService;
import com.Messaging_System.domain.enums.FriendRequestResponseType;
import com.Messaging_System.domain.enums.WebsocketResponseType;
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

@Service
@RequiredArgsConstructor
public class WebsocketNotificationService {

    private final WebsocketSessionService sessionService;
    private final ObjectMapper objectMapper;
    private final UserService userService;

    public void userFeedbackFromFriendRequest(UserFriendsModel userFriendsModel) throws IOException {
        UserModel user = userFriendsModel.getUser();

        WebSocketSession friendSession = sessionService.getSessionByUser(user);

        if(friendSession == null){
            return;
        }
        friendSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(
                new GenericSuccessfullDTO(
                        LocalDateTime.now().toString(),
                        200,
                        "Successfully sent friend request!"
                )
        )));
    }

    public void notifyTargetRequest(UserFriendsModel userFriendsModel) throws IOException {
        UserModel user = userFriendsModel.getUser();
        UserModel friend = userFriendsModel.getFriend();

        WebSocketSession friendSession = sessionService.getSessionByUser(friend);

        if(friendSession == null){
            return;
        }

        friendSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(
                new WebsocketFriendRequestNotify(
                        userService.formatUserAsDTO(user)
                )
        )));

    }

    public void sendUserMessage(WebsocketMessageDTO message, UserModel sender) throws IOException {
        UserModel target = userService.findUserByFullUsername(message.getReceiverName());

        WebSocketSession friendSession = sessionService.getSessionByUser(target);
        WebSocketSession userSession = sessionService.getSessionByUser(sender);

        if(friendSession == null){
            return;
        }

        friendSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(
                new WebsocketMessageNotifyDTO(
                        WebsocketResponseType.MESSAGE,
                        message.getContent(),
                        target.getName() + "#" + target.getTag()
                )
        )));

        userSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(
                new GenericSuccessfullDTO(
                        LocalDateTime.now().toString(),
                        200,
                        "successfully send message to target"
                )
        )));
    }

    public void deleteUserMessageNotification(WebsocketMessageDTO message, UserModel sender){
        UserModel target = userService.findUserByFullUsername(message.getReceiverName());
        WebSocketSession friendSession = sessionService.getSessionByUser(target);
        WebSocketSession senderSession = sessionService.getSessionByUser(sender);



    }

    public void readUserMessageNotification(WebsocketMessageDTO message){
        UserModel target = userService.findUserByFullUsername(message.getReceiverName());
        WebSocketSession session = sessionService.getSessionByUser(target);
    }

    public void markAsNotReadUserMessageNotification(WebsocketMessageDTO message){
        UserModel target = userService.findUserByFullUsername(message.getReceiverName());
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
