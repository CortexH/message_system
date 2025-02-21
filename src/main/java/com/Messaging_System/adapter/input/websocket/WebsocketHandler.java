package com.Messaging_System.adapter.input.websocket;

import com.Messaging_System.application.service.websocket.WebsocketService;
import com.Messaging_System.application.service.websocket.WebsocketSessionService;
import com.Messaging_System.domain.model.UserModel;
import com.Messaging_System.infrastructure.cache.WebsocketSessionRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebsocketHandler extends TextWebSocketHandler {

    private final WebsocketService websocketService;
    private final WebsocketSessionService sessionService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        UserModel user = (UserModel) session.getAttributes().get("User");
        sessionService.insertIntoSession(user.getUuid().toString(), session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
       websocketService.receiveUserMessage(
               session, message
       );
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

    }
}
