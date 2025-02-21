package com.Messaging_System.infrastructure.mapper;

import com.Messaging_System.domain.model.UserFriendsModel;
import com.Messaging_System.infrastructure.entity.UserFriendsEntity;

public class UserFriendsMapper {

    public static UserFriendsModel toModel(UserFriendsEntity entity){
        return UserFriendsModel.builder()
                .user_friends_id(entity.getUser_friends_id())
                .friend(UserMapper.toModel(entity.getFriend()))
                .state(entity.getState())
                .user(UserMapper.toModel(entity.getUserEntity()))
                .build();
    }

    public static UserFriendsEntity toEntity(UserFriendsModel model){
        return UserFriendsEntity.builder()
                .user_friends_id(model.getUser_friends_id())
                .friend(UserMapper.toEntity(model.getFriend()))
                .state(model.getState())
                .userEntity(UserMapper.toEntity(model.getUser()))
                .build();
    }

}
