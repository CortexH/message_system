package com.Messaging_System.application.service;

import com.Messaging_System.application.dto.input.WebsocketMessageDTO;
import com.Messaging_System.application.dto.output.DTO_ExGeneric;
import com.Messaging_System.domain.model.UserModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
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
            //session.sendMessage(new TextMessage("skibidi dop dop dop dop"));
            WebsocketMessageDTO receivedMessage = objectMapper.readValue(message.getPayload().toString(), WebsocketMessageDTO.class);

            messageService.sendMessage(
                    receivedMessage.getContent(),
                    UUID.fromString(receivedMessage.getReceiverId()),
                    session.getHandshakeHeaders().getFirst("authorization")
            );

            sendUserMessage(userService.findUserById(UUID.fromString(receivedMessage.getReceiverId())));



        } catch (NoSuchElementException e) {
            objectMapper.registerModule(new JavaTimeModule());
            String json = objectMapper.writeValueAsString(new DTO_ExGeneric(
                    LocalDateTime.now(),
                    404,
                    "Not found",
                    e.getMessage()
            ));
            session.sendMessage(new TextMessage(json));
        }
    }

    // lança uma mensagem ao usuário
    public void sendUserMessage(UserModel user){
        WebSocketSession session = sessions.get(user.getUuid().toString());

        if(session == null){

            return;
        }



    }

}
