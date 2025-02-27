package com.Messaging_System.domain.model;

import com.Messaging_System.domain.enums.FriendRequestState;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserFriendsModel {

    private UUID user_friends_id;

    private UserModel user;

    private UserModel friend;

    private FriendRequestState state;

}
