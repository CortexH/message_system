package com.Messaging_System.infrastructure.config;

import com.Messaging_System.adapter.input.websocket.WebsocketHandler;
import com.Messaging_System.adapter.input.websocket.WebsocketInterceptor;
import com.Messaging_System.application.service.websocket.WebsocketService;
import com.Messaging_System.application.service.websocket.WebsocketSessionService;
import com.Messaging_System.application.sharedServices.UserContextService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebsocketBeansConfig {

    @Bean
    public WebsocketHandler websocketHandler(WebsocketService websocketService, WebsocketSessionService sessionService){
        return new WebsocketHandler(websocketService, sessionService);
    }

    @Bean
    public WebsocketInterceptor websocketInterceptor(UserContextService contextService){
        return new WebsocketInterceptor(contextService);
    }

}
