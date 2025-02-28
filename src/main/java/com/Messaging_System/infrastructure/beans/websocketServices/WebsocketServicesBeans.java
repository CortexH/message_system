package com.Messaging_System.infrastructure.beans.websocketServices;

import com.Messaging_System.application.service.MessageService;
import com.Messaging_System.application.service.UserService;
import com.Messaging_System.application.service.websocket.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebsocketServicesBeans {

    @Bean
    public WebsocketService websocketService(
            WebsocketSessionService sessionService,
            MessageService messageService,
            ObjectMapper objectMapper,
            UserService userService,
            ApplicationEventPublisher applicationEventPublisher,
            WebsocketFriendRequestService friendRequestService,
            WebsocketExceptionService websocketExceptionService
    ){

        objectMapper.registerModule(new JavaTimeModule());

        return new WebsocketService(
                sessionService, messageService, objectMapper,
                userService, applicationEventPublisher, friendRequestService,
                websocketExceptionService
        );
    }

    @Bean
    public WebsocketNotificationService websocketNotificationService(
            WebsocketSessionService websocketSessionService,
            ObjectMapper objectMapper,
            UserService userService,
            MessageService messageService
    ){
        objectMapper.registerModule(new JavaTimeModule());
        return new WebsocketNotificationService(websocketSessionService, objectMapper, userService, messageService);
    }

}
