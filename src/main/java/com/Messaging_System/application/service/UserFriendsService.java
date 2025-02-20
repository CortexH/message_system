package com.Messaging_System.application.service;

import com.Messaging_System.application.port.UserFriendsRepositoryPort;
import com.Messaging_System.domain.enums.FriendRequestState;
import com.Messaging_System.domain.model.UserFriendsModel;
import com.Messaging_System.domain.model.UserModel;
import com.Messaging_System.domain.service.userFriends.UserFriendsValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFriendsService {

    private final UserFriendsRepositoryPort repository;
    private final UserFriendsValidationService validationService;

    public void sendFriendRequest(UserModel user, UserModel friend){
        validationService.validateIfAlreadyExistsFriendRequest(user, friend);
        UserFriendsModel request = UserFriendsModel.builder()
                .state(FriendRequestState.PENDING)
                .userEntity(user)
                .friend(friend)
                .build();

        repository.sendFriendRequest(request);
    }

    public void acceptFriendRequest(UserModel user, UserModel friend){
        repository.acceptFriendRequest(user, friend);
    }

    public void declineFriendRequest(UserModel user, UserModel friend){
        repository.declineFriendRequest(user, friend);
    }

    public void sendFriendRequestToTarget(UserModel sender, UserModel target){

    }

    public UserFriendsModel getUserFriendByUserAndFriend(UserModel user, UserModel friend){
        return repository.findByUserAndFriend(user, friend);
    }

}
