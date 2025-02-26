package com.Messaging_System.application.event.listeners;

import com.Messaging_System.application.event.sentEvent.User_ReturnFriendRequest;
import com.Messaging_System.application.service.UserFriendsService;
import com.Messaging_System.application.service.UserService;
import com.Messaging_System.application.service.websocket.WebsocketNotificationService;
import com.Messaging_System.domain.model.UserFriendsModel;
import com.Messaging_System.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class HandleSendUserRequest {

    private final WebsocketNotificationService websocketService;
    private final UserFriendsService friendsService;
    private final UserService userService;

    @EventListener
    public void handleUserFriendRequest(User_ReturnFriendRequest event) throws IOException {
        UserModel user = userService.findUserByFullUsername(event.getData().requestedUserName());
        UserFriendsModel friendsModel = friendsService.getUserFriendByUserAndFriend(event.getSender(), user);
        websocketService.notifyTargetRequest(friendsModel);
        websocketService.userFeedbackFromFriendRequest(friendsModel);
    }



}
