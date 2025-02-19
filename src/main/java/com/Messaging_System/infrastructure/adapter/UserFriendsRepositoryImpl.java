package com.Messaging_System.infrastructure.adapter;

import com.Messaging_System.application.port.UserFriendsRepositoryPort;
import com.Messaging_System.domain.model.UserModel;
import com.Messaging_System.infrastructure.entity.UserEntity;
import com.Messaging_System.infrastructure.entity.UserFriendsEntity;
import com.Messaging_System.infrastructure.mapper.UserMapper;
import com.Messaging_System.infrastructure.repository.UserFriendsRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserFriendsRepositoryImpl implements UserFriendsRepositoryPort {

    private final UserFriendsRepository repository;

    @Override
    public void addFriend(UserEntity userEntity, UserEntity friend) {

    }

    @Override
    public List<UserModel> getUserFriends(UserModel user) {
        List<UserFriendsEntity> userFriends = repository.findUserFriends(user.getUuid());

        List<UserModel> models = new ArrayList<>();
        for(UserFriendsEntity uf : userFriends){
            UserEntity u = uf.getFriend();

            models.add(UserMapper.toModel(u));
        }
        return models;
    }
}
