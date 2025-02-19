package com.Messaging_System.application.port;

import com.Messaging_System.domain.model.UserModel;
import com.Messaging_System.infrastructure.entity.UserEntity;

import java.util.List;

public interface UserFriendsRepositoryPort {
    void addFriend(UserEntity userEntity, UserEntity friend);
    List<UserModel> getUserFriends(UserModel user);

}
