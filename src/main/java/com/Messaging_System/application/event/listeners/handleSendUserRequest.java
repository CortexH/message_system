package com.Messaging_System.application.event.listeners;

import com.Messaging_System.application.event.sentEvent.User_ReturnFriendRequest;
import com.Messaging_System.application.service.UserFriendsService;
import com.Messaging_System.application.service.websocket.WebsocketNotificationService;
import com.Messaging_System.application.service.websocket.WebsocketService;
import com.Messaging_System.domain.model.UserFriendsModel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class handleSendUserRequest {

    private final WebsocketNotificationService websocketService;
    private final UserFriendsService friendsService;

    @EventListener
    public void handleUserFriendRequest(User_ReturnFriendRequest event) throws IOException {
        UserFriendsModel friendsModel = friendsService.getUserFriendByUserAndFriend(event.getSender(), event.getTarget());
        websocketService.notifyTargetRequest(friendsModel);
        websocketService.userFeedbackFromFriendRequest(friendsModel);
    }

}
