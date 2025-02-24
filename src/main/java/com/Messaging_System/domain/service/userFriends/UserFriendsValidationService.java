package com.Messaging_System.domain.service.userFriends;

import com.Messaging_System.adapter.exception.CustomBadRequestException;
import com.Messaging_System.application.port.UserFriendsRepositoryPort;
import com.Messaging_System.domain.enums.FriendRequestState;
import com.Messaging_System.domain.model.UserModel;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class UserFriendsValidationService {

    private final UserFriendsRepositoryPort repository;

    public Boolean validateIfUserIsFriendOf(
            UserModel user,
            UserModel friend
    ){
        List<UserModel> allFriends = repository.getUserFriends(user);
        return allFriends.stream().anyMatch(i -> i.getUuid().equals(friend.getUuid()));
    }

    public Boolean validateIfAlreadyExistsFriendRequest(UserModel user, UserModel friend){
        return repository.validateIfUserCanSendFriendRequest(user, friend);
    }

    public Boolean returnTrueIfTargetIsSameAsUser(UserModel user, UserModel friend){
        return (user.getUuid().equals(friend.getUuid()));
    }

    public Boolean validateStateOfFriendRequest(UserModel user, UserModel friend, FriendRequestState state){
        List<UserModel> users = repository.getUserFriendsWithType(user, state);

        return users.stream().anyMatch(i -> i.getUuid().equals(friend.getUuid()));

    }

    public Boolean validateIfUserCanSendMessageToReceiver(UserModel messageSender, UserModel messageReceiver){
        return repository.validateIfUserCanSendFriendRequest(messageSender, messageReceiver);
    }

}
