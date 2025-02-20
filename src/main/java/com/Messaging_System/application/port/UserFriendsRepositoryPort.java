package com.Messaging_System.application.port;

import com.Messaging_System.domain.enums.FriendRequestState;
import com.Messaging_System.domain.model.UserFriendsModel;
import com.Messaging_System.domain.model.UserModel;
import com.Messaging_System.infrastructure.entity.UserEntity;

import java.util.List;

public interface UserFriendsRepositoryPort {
    List<UserModel> getUserFriends(UserModel user);
    List<UserModel> getUserFriendsWithType(UserModel user, FriendRequestState state);

    void sendFriendRequest(UserFriendsModel request);
    void acceptFriendRequest(UserModel user, UserModel friend);
    void declineFriendRequest(UserModel user, UserModel friend);

    Boolean returnTrueIfAlreadyHasAcceptedOrSentFriendRequest(UserModel user, UserModel friend);

    UserFriendsModel findByUserAndFriend(UserModel user, UserModel friend);
}
