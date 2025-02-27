package com.Messaging_System.application.service.websocket;

import com.Messaging_System.application.dto.input.WebsocketFriendRequestDTO;
import com.Messaging_System.application.dto.input.WebsocketRequestDTO;
import com.Messaging_System.application.event.sentEvent.UserReturnFriendRequest;
import com.Messaging_System.application.service.UserFriendsService;
import com.Messaging_System.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebsocketFriendRequestService {
    private final ApplicationEventPublisher eventPublisher;
    private final UserFriendsService friendsService;

    public void handleFriendRequest(UserModel requisitionSender, WebsocketRequestDTO request){
        WebsocketFriendRequestDTO data = request.friendRequest();

        switch (data.type()){
            case SEND -> {
                friendsService.sendFriendRequest(requisitionSender, data);
                eventPublisher.publishEvent(new UserReturnFriendRequest(
                        this, requisitionSender, data));
            }
            case ACCEPT -> friendsService.acceptFriendRequest(requisitionSender, data);
            case DECLINE -> friendsService.declineFriendRequest(requisitionSender, data);
            case REMOVE -> friendsService.removeFriend(requisitionSender, data);
        }

    }

}
