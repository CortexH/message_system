package com.Messaging_System.application.service.websocket;

import com.Messaging_System.adapter.exception.CustomBadRequestException;
import com.Messaging_System.application.dto.input.WebsocketRequestDTO;
import com.Messaging_System.application.service.MessageService;
import com.Messaging_System.application.service.UserService;
import com.Messaging_System.domain.model.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class WebsocketService {

    private final WebsocketSessionService sessionService;
    private final MessageService messageService;
    private final ObjectMapper objectMapper;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;
    private final WebsocketFriendRequestService friendRequestService;
    private final WebsocketExceptionService websocketExceptionService;

    // ao usuário mandar uma mensagem, recebe por aqui
    public void receiveUserMessage(
            WebSocketSession session,
            WebSocketMessage<?> message
    ) throws Exception {
        try{
            WebsocketRequestDTO receivedMessage = objectMapper.readValue(message.getPayload().toString(), WebsocketRequestDTO.class);
            UserModel user = (UserModel) session.getAttributes().get("User");

            switch (receivedMessage.type()){
                case MESSAGE -> messageService.handleUserMessage(user, receivedMessage);
                case FRIEND_REQUEST -> friendRequestService.handleFriendRequest(user, receivedMessage);
            }

        } catch (NoSuchElementException e) {
            websocketExceptionService.handleNoSuchElementException(e, session);

        } catch (CustomBadRequestException e){
            websocketExceptionService.handleBadRequestException(e, session);
        } //catch (Exception e){
            //websocketExceptionService.handleOtherExceptions(session);
        //}
    }

}
