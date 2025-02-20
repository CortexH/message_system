package com.Messaging_System.application.service;

import com.Messaging_System.adapter.exception.CustomBadRequestException;
import com.Messaging_System.application.dto.input.WebsocketRequestDTO;
import com.Messaging_System.application.dto.output.exceptions.DTO_ExGeneric;
import com.Messaging_System.application.event.sentEvent.User_ReturnFriendRequest;
import com.Messaging_System.domain.enums.FriendRequestType;
import com.Messaging_System.domain.enums.WebsocketResponseType;
import com.Messaging_System.domain.model.UserFriendsModel;
import com.Messaging_System.domain.model.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class WebsocketService {

    private final MessageService messageService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final UserService userService;
    private final UserFriendsService friendsService;
    private final ApplicationEventPublisher eventPublisher;

    public void newSession(WebSocketSession session){
        UserModel user = (UserModel) session.getAttributes().get("User");
        sessions.put(user.getUuid().toString(), session);
    }

    // ao usuário mandar uma mensagem, recebe por aqui
    public void receiveUserMessage(
            WebSocketSession session,
            WebSocketMessage<?> message
    ) throws Exception {
        try{
            WebsocketRequestDTO receivedMessage = objectMapper.readValue(message.getPayload().toString(), WebsocketRequestDTO.class);

            UserModel user = (UserModel) session.getAttributes().get("User");

            if(receivedMessage.type() == WebsocketResponseType.MESSAGE){
                UUID target_User = UUID.fromString(receivedMessage.message().getReceiverId());

                messageService.sendMessage(
                        receivedMessage.message().getContent(),
                        target_User,
                        user
                );
                answerUser(userService.findUserById(target_User));
            }

            if(receivedMessage.type() == WebsocketResponseType.FRIEND_REQUEST){
                FriendRequestType requestType = receivedMessage.friendRequest().type();
                String targetName = receivedMessage.friendRequest().requestedUserName();
                UserModel targetUser = userService.findUserByFullUsername(targetName);

                if(requestType == FriendRequestType.SEND){
                    friendsService.sendFriendRequest(user, targetUser);

                }else if(requestType == FriendRequestType.ACCEPT) {
                    friendsService.acceptFriendRequest(user, targetUser);

                }else if(requestType == FriendRequestType.DECLINE){
                    friendsService.declineFriendRequest(user, targetUser);

                }else{
                    throw new CustomBadRequestException("Incorrect request type.");
                }

                eventPublisher.publishEvent(new User_ReturnFriendRequest(
                        this, user, targetUser));

            }


        } catch (NoSuchElementException e) {
            objectMapper.registerModule(new JavaTimeModule());
            String json = objectMapper.writeValueAsString(new DTO_ExGeneric(
                    LocalDateTime.now(),
                    404,
                    "Not found",
                    e.getMessage()
            ));
            session.sendMessage(new TextMessage(json));

        } catch (CustomBadRequestException e){
            objectMapper.registerModule(new JavaTimeModule());
            String json = objectMapper.writeValueAsString(new DTO_ExGeneric(
                    LocalDateTime.now(),
                    400,
                    "Bad request",
                    e.getMessage()
            ));
            session.sendMessage(new TextMessage(json));
        }
    }

    public void sendUserRequestToTarget(UserFriendsModel friend){

    }

    // lança uma mensagem ao usuário
    public void answerUser(UserModel user){
        WebSocketSession session = sessions.get(user.getUuid().toString());

        if(session == null){

            return;
        }


    }

}
