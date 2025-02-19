package com.Messaging_System.adapter.input.websocket;

import com.Messaging_System.application.service.WebsocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebsocketHandler extends TextWebSocketHandler {

    private final WebsocketService websocketService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        websocketService.newSession(session);
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
