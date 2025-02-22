package com.Messaging_System.application.service;

import com.Messaging_System.application.dto.input.WebsocketMessageDTO;
import com.Messaging_System.application.dto.input.WebsocketRequestDTO;
import com.Messaging_System.application.port.MessageRepositoryPort;
import com.Messaging_System.application.sharedServices.UserContextService;
import com.Messaging_System.domain.model.UserModel;
import com.Messaging_System.domain.service.userFriends.UserFriendsValidationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepositoryPort repository;
    private final UserService userService;
    private final UserContextService userContextService;
    private final UserFriendsValidationService friendsValidationService;

    public void sendMessage(
            UserModel user, WebSocketSession session,
            WebsocketRequestDTO receivedMessage
    ){

    }

}
