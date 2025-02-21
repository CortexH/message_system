package com.Messaging_System.infrastructure.config;

import com.Messaging_System.application.port.UserFriendsRepositoryPort;
import com.Messaging_System.application.port.UserRepositoryPort;
import com.Messaging_System.application.service.MessageService;
import com.Messaging_System.application.service.UserFriendsService;
import com.Messaging_System.application.service.UserService;
import com.Messaging_System.application.service.websocket.WebsocketNotificationService;
import com.Messaging_System.application.service.websocket.WebsocketService;
import com.Messaging_System.application.service.websocket.WebsocketSessionService;
import com.Messaging_System.domain.service.user.BusinessRuleApplicationService;
import com.Messaging_System.domain.service.user.UserAuthenticationService;
import com.Messaging_System.domain.service.user.UserEncryptionService;
import com.Messaging_System.domain.service.user.UserValidationService;
import com.Messaging_System.domain.service.userFriends.UserFriendsValidationService;
import com.Messaging_System.infrastructure.security.BCryptService;
import com.Messaging_System.infrastructure.security.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralBeansConfigurations {

    @Bean
    public UserEncryptionService userEncryptionService(BCryptService service){
        return new UserEncryptionService(service);
    }

    @Bean
    public UserValidationService userValidationService(UserRepositoryPort repositoryPort, UserEncryptionService encryptionService){
        return new UserValidationService(repositoryPort, encryptionService);
    }

    @Bean
    public UserAuthenticationService userAuthenticationService(JwtService jwtService){
        return new UserAuthenticationService(jwtService);
    }

    @Bean
    public UserFriendsValidationService userFriendsValidationService(UserFriendsRepositoryPort repositoryPort){
        return new UserFriendsValidationService(repositoryPort);
    }

    @Bean
    public BusinessRuleApplicationService businessRuleApplicationService(UserRepositoryPort repositoryPort){
        return new BusinessRuleApplicationService(repositoryPort);
    }

    @Bean
    public WebsocketService websocketService(
            MessageService messageService,
            ObjectMapper objectMapper,
            UserService userService,
            UserFriendsService userFriendsService,
            WebsocketSessionService sessionService,
            ApplicationEventPublisher applicationEventPublisher
    ){

        objectMapper.registerModule(new JavaTimeModule());

        return new WebsocketService(
                sessionService,
                messageService, objectMapper, userService,
                userFriendsService, applicationEventPublisher
        );
    }

    @Bean
    public WebsocketNotificationService websocketNotificationService(
            WebsocketSessionService websocketSessionService,
            ObjectMapper objectMapper,
            UserService userService
    ){
        objectMapper.registerModule(new JavaTimeModule());
        return new WebsocketNotificationService(websocketSessionService, objectMapper, userService);
    }

}
