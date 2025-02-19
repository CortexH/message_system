package com.Messaging_System.infrastructure.config;

import com.Messaging_System.adapter.input.websocket.WebsocketHandler;
import com.Messaging_System.adapter.input.websocket.WebsocketInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebsocketConfig implements WebSocketConfigurer {

    private final WebsocketInterceptor websocketInterceptor;
    private final WebsocketHandler websocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(websocketHandler, "/user-message-ws").addInterceptors(websocketInterceptor)
                .setAllowedOrigins("*");
    }
}
