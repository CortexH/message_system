package com.Messaging_System.application.service.websocket;

import com.Messaging_System.adapter.exception.CustomBadRequestException;
import com.Messaging_System.application.dto.output.exceptions.DTO_ExGeneric;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class WebsocketExceptionService {

    private final ObjectMapper objectMapper;

    public void handleNoSuchElementException(NoSuchElementException ex, WebSocketSession session) throws IOException {
        String json = objectMapper.writeValueAsString(new DTO_ExGeneric(
                LocalDateTime.now(),
                404,
                "Not found",
                ex.getMessage()
        ));
        session.sendMessage(new TextMessage(json));
    }

    public void handleBadRequestException(CustomBadRequestException ex, WebSocketSession session) throws IOException {
        String json = objectMapper.writeValueAsString(new DTO_ExGeneric(
                LocalDateTime.now(),
                400,
                "Bad request",
                ex.getMessage()
        ));
        session.sendMessage(new TextMessage(json));
    }

    public void handleOtherExceptions(WebSocketSession session) throws IOException {
        String json = objectMapper.writeValueAsString(
                new DTO_ExGeneric(
                        LocalDateTime.now(),
                        500,
                        "Internal error",
                        "An internal error ocurred. Try again later"
                )
        );

        session.sendMessage(new TextMessage(json));

    }

}
