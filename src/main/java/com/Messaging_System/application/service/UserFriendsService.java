package com.Messaging_System.application.service;

import com.Messaging_System.adapter.exception.CustomBadRequestException;
import com.Messaging_System.application.dto.input.WebsocketFriendRequestDTO;
import com.Messaging_System.application.event.sentEvent.UserFriendRequestResponse;
import com.Messaging_System.application.port.UserFriendsRepositoryPort;
import com.Messaging_System.domain.enums.FriendRequestResponseType;
import com.Messaging_System.domain.enums.FriendRequestState;
import com.Messaging_System.domain.model.UserFriendsModel;
import com.Messaging_System.domain.model.UserModel;
import com.Messaging_System.domain.service.userFriends.UserFriendsValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserFriendsService {

    private final UserFriendsRepositoryPort repository;
    private final UserFriendsValidationService validationService;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

    public void sendFriendRequest(UserModel user, WebsocketFriendRequestDTO data){
        UserModel friend = userService.findUserByFullUsername(data.requestedUserName());
        if(validationService.validateIfAlreadyExistsFriendRequest(user, friend)) throw new CustomBadRequestException("There is already a friend request to this user!");
        if(validationService.returnTrueIfTargetIsSameAsUser(user, friend)) throw new CustomBadRequestException("You cannot friend yourself");

        UserFriendsModel request = UserFriendsModel.builder()
                .state(FriendRequestState.PENDING)
                .user(user)
                .friend(friend)
                .build();

        repository.sendFriendRequest(request);
    }

    public void acceptFriendRequest(UserModel friend, WebsocketFriendRequestDTO data){
        UserModel sender = userService.findUserByFullUsername(data.senderUserName());

        if(validationService.returnTrueIfTargetIsSameAsUser(sender, friend)) throw new CustomBadRequestException("You have no friend request of specified user");
        if(validationService.validateIfUserIsFriendOf(sender, friend)) throw new CustomBadRequestException("You have no friend request of specified user");
        if(!validationService.validateIfAlreadyExistsFriendRequest(sender, friend)) throw new CustomBadRequestException("You does not have a friend request of specified user");

        repository.acceptFriendRequest(friend, sender);

        eventPublisher.publishEvent(new UserFriendRequestResponse(this, sender, friend, FriendRequestResponseType.ACCEPTED));
    }

    public void declineFriendRequest(UserModel friend, WebsocketFriendRequestDTO data){
        UserModel sender = userService.findUserByFullUsername(data.senderUserName());

        if(validationService.returnTrueIfTargetIsSameAsUser(sender, friend)) throw new CustomBadRequestException("You have no friend request of specified user");
        if(validationService.validateIfUserIsFriendOf(sender, friend)) throw new CustomBadRequestException("You have no friend request of specified user");
        if(!validationService.validateIfAlreadyExistsFriendRequest(sender, friend)) throw new CustomBadRequestException("You does not have a friend request of specified user");

        repository.declineFriendRequest(sender, friend);
    }

    public void removeFriend(UserModel sender, WebsocketFriendRequestDTO data){
        UserModel friend = userService.findUserByFullUsername(data.senderUserName());
        if(!repository.validateIfUserIsFriendOrFriended(sender, friend)) throw new CustomBadRequestException("You are not friend of this user");
        if(validationService.returnTrueIfTargetIsSameAsUser(sender, friend)) throw new CustomBadRequestException("You have no friend request of specified user");
        if(validationService.validateIfUserIsFriendOf(sender, friend)) throw new CustomBadRequestException("You have no friend request of specified user");

    }

    public UserFriendsModel getUserFriendByUserAndFriend(UserModel user, UserModel friend){
        return repository.findByUserAndFriend(user, friend);
    }

    // shared

    public List<UserModel> getUserFriends(UserModel user){
        return repository.getUserFriendsAndUserFriendedAs(user);
    }
}
