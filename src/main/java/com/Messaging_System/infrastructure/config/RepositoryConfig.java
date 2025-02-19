package com.Messaging_System.infrastructure.config;

import com.Messaging_System.application.port.MessageRepositoryPort;
import com.Messaging_System.application.port.UserFriendsRepositoryPort;
import com.Messaging_System.application.port.UserRepositoryPort;
import com.Messaging_System.infrastructure.repository.MessageRepository;
import com.Messaging_System.infrastructure.repository.UserFriendsRepository;
import com.Messaging_System.infrastructure.repository.UserRepository;
import com.Messaging_System.infrastructure.adapter.MessageRepositoryImpl;
import com.Messaging_System.infrastructure.adapter.UserFriendsRepositoryImpl;
import com.Messaging_System.infrastructure.adapter.UserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public UserRepositoryPort userRepositoryPort(UserRepository userRepository){
        return new UserRepositoryImpl(userRepository);
    }

    @Bean
    public MessageRepositoryPort messageRepositoryPort(MessageRepository messageRepository){
        return new MessageRepositoryImpl(messageRepository);
    }

    @Bean
    public UserFriendsRepositoryPort userFriendsRepositoryPort(UserFriendsRepository repository){
        return new UserFriendsRepositoryImpl(repository);
    }
}
