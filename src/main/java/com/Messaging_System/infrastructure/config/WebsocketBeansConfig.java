package com.Messaging_System.infrastructure.config;

import com.Messaging_System.adapter.input.websocket.WebsocketHandler;
import com.Messaging_System.adapter.input.websocket.WebsocketInterceptor;
import com.Messaging_System.application.service.MessageService;
import com.Messaging_System.application.service.WebsocketService;
import com.Messaging_System.application.sharedServices.UserContextService;
import com.Messaging_System.infrastructure.security.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebsocketBeansConfig {

    @Bean
    public WebsocketHandler websocketHandler(WebsocketService websocketService){
        return new WebsocketHandler(websocketService);
    }

    @Bean
    public WebsocketInterceptor websocketInterceptor(UserContextService contextService){
        return new WebsocketInterceptor(contextService);
    }

}
