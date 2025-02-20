package com.Messaging_System.application.service;

import com.Messaging_System.application.port.MessageRepositoryPort;
import com.Messaging_System.application.sharedServices.UserContextService;
import com.Messaging_System.domain.model.UserModel;
import com.Messaging_System.domain.service.userFriends.UserFriendsValidationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepositoryPort repository;
    private final UserService userService;
    private final UserContextService userContextService;
    private final UserFriendsValidationService friendsValidationService;

    public void sendMessage(
            String message,
            UUID receiver_id,
            UserModel user
    ){

        UserModel receiver = userService.findUserById(receiver_id);
        friendsValidationService.validateIfUserIsFriendOf(user, receiver);



    }

}
