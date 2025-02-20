package com.Messaging_System.application.event.listeners;

import com.Messaging_System.application.event.sentEvent.User_ReturnFriendRequest;
import com.Messaging_System.application.event.sentEvent.User_SendMessage;
import com.Messaging_System.application.service.UserFriendsService;
import com.Messaging_System.application.service.WebsocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class handleSendUserRequest {

    private final WebsocketService websocketService;
    private final UserFriendsService friendsService;

    @EventListener
    public void handleUserFriendRequest(User_ReturnFriendRequest event){

    }

}
