package com.Messaging_System.infrastructure.beans;

import com.Messaging_System.application.port.UserFriendsRepositoryPort;
import com.Messaging_System.application.port.UserRepositoryPort;
import com.Messaging_System.application.service.MessageService;
import com.Messaging_System.application.service.UserService;
import com.Messaging_System.application.service.websocket.*;
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

}
