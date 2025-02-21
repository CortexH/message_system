package com.Messaging_System.domain.service.userFriends;

import com.Messaging_System.adapter.exception.CustomBadRequestException;
import com.Messaging_System.application.port.UserFriendsRepositoryPort;
import com.Messaging_System.domain.model.UserModel;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class UserFriendsValidationService {

    private final UserFriendsRepositoryPort repository;

    public void validateIfUserIsFriendOf(UserModel user, UserModel friend){
        List<UserModel> allFriends = repository.getUserFriends(user);

        for(UserModel model : allFriends){
            if(model.getUuid().equals(friend.getUuid())) return;
        }
        throw new CustomBadRequestException("You are not friend of specified user");
    }

    public void validateIfAlreadyExistsFriendRequest(UserModel user, UserModel friend){
        if(repository.returnTrueIfAlreadyHasAcceptedOrSentFriendRequest(user, friend)){
            throw new CustomBadRequestException("You already have a friend request of this user!");
        }
    }

    public void validateIfTargetIsSameAsUser(UserModel user, UserModel friend){
        if(user.getUuid().equals(friend.getUuid())) throw new CustomBadRequestException("You cannot friend yourself!");
    }

}
