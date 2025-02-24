package com.Messaging_System.infrastructure.mapper;

import com.Messaging_System.domain.model.UserModel;
import com.Messaging_System.infrastructure.entity.UserEntity;

public class UserMapper {

    public static UserEntity toEntity(UserModel user){
        return UserEntity.builder()
                .role(user.getRole())
                .email(user.getEmail())
                .name(user.getName())
                .userBio(user.getUserBio())
                .password(user.getPassword())
                .userImage(user.getUserImage())
                .uuid(user.getUuid())
                .tag(user.getTag())
                .build();
    }

    public static UserModel toModel(UserEntity user){
        return UserModel.builder()
                .role(user.getRole())
                .email(user.getEmail())
                .name(user.getName())
                .userBio(user.getUserBio())
                .password(user.getPassword())
                .userImage(user.getUserImage())
                .uuid(user.getUuid())
                .tag(user.getTag())
                .build();
    }

}
