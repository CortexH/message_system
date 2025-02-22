package com.Messaging_System.application.service.websocket;

import com.Messaging_System.application.dto.input.WebsocketFriendRequestDTO;
import com.Messaging_System.application.dto.input.WebsocketRequestDTO;
import com.Messaging_System.application.event.sentEvent.User_ReturnFriendRequest;
import com.Messaging_System.application.service.UserFriendsService;
import com.Messaging_System.application.service.UserService;
import com.Messaging_System.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebsocketFriendRequestService {
    private final ApplicationEventPublisher eventPublisher;
    private final UserFriendsService friendsService;
    private final UserService userService;

    public void handleFriendRequest(UserModel sender, WebsocketRequestDTO request){
        WebsocketFriendRequestDTO data = request.friendRequest();
        UserModel target = userService.findUserByFullUsername(data.requestedUserName());

        switch (data.type()){
            case SEND -> friendsService.sendFriendRequest(sender, target);
            case ACCEPT -> friendsService.acceptFriendRequest(sender, target);
            case DECLINE -> friendsService.declineFriendRequest(sender, target);
        }

        eventPublisher.publishEvent(new User_ReturnFriendRequest(
                this, sender, target));
    }

}
