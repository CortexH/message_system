package com.Messaging_System.application.service.websocket;

import com.Messaging_System.adapter.exception.CustomBadRequestException;
import com.Messaging_System.application.dto.input.WebsocketRequestDTO;
import com.Messaging_System.application.dto.output.exceptions.DTO_ExGeneric;
import com.Messaging_System.application.event.sentEvent.User_ReturnFriendRequest;
import com.Messaging_System.application.service.MessageService;
import com.Messaging_System.application.service.UserFriendsService;
import com.Messaging_System.application.service.UserService;
import com.Messaging_System.domain.enums.FriendRequestType;
import com.Messaging_System.domain.enums.WebsocketResponseType;
import com.Messaging_System.domain.model.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class WebsocketService {

    private final WebsocketSessionService sessionService;
    private final MessageService messageService;
    private final ObjectMapper objectMapper;
    private final UserService userService;
    private final UserFriendsService friendsService;
    private final ApplicationEventPublisher eventPublisher;
    private final WebsocketFriendRequestService friendRequestService;

    // ao usuário mandar uma mensagem, recebe por aqui
    public void receiveUserMessage(
            WebSocketSession session,
            WebSocketMessage<?> message
    ) throws Exception {
        try{
            WebsocketRequestDTO receivedMessage = objectMapper.readValue(message.getPayload().toString(), WebsocketRequestDTO.class);
            UserModel user = (UserModel) session.getAttributes().get("User");

            switch (receivedMessage.type()){

                case MESSAGE -> messageService.sendMessage(user, session, receivedMessage);

                case FRIEND_REQUEST -> {
                    friendRequestService.handleFriendRequest(user, receivedMessage);
                    UserModel targetUser = userService.findUserByFullUsername(receivedMessage.friendRequest().requestedUserName());
                    eventPublisher.publishEvent(new User_ReturnFriendRequest(
                            this, user, targetUser));
                }
            }

        } catch (NoSuchElementException e) {
            String json = objectMapper.writeValueAsString(new DTO_ExGeneric(
                    LocalDateTime.now(),
                    404,
                    "Not found",
                    e.getMessage()
            ));
            session.sendMessage(new TextMessage(json));

        } catch (CustomBadRequestException e){
            String json = objectMapper.writeValueAsString(new DTO_ExGeneric(
                    LocalDateTime.now(),
                    400,
                    "Bad request",
                    e.getMessage()
            ));
            session.sendMessage(new TextMessage(json));
        }
    }

    // lança uma mensagem ao usuário
    public void answerUser(UserModel user){
        WebSocketSession session = sessionService.getSessionByUser(user);

        if(session == null){
            return;
        }

    }

}
