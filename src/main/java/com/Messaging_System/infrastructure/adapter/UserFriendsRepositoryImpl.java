package com.Messaging_System.infrastructure.adapter;

import com.Messaging_System.application.port.UserFriendsRepositoryPort;
import com.Messaging_System.domain.enums.FriendRequestState;
import com.Messaging_System.domain.model.UserFriendsModel;
import com.Messaging_System.domain.model.UserModel;
import com.Messaging_System.infrastructure.entity.UserEntity;
import com.Messaging_System.infrastructure.entity.UserFriendsEntity;
import com.Messaging_System.infrastructure.mapper.UserFriendsMapper;
import com.Messaging_System.infrastructure.mapper.UserMapper;
import com.Messaging_System.infrastructure.repository.UserFriendsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class UserFriendsRepositoryImpl implements UserFriendsRepositoryPort {

    private final UserFriendsRepository repository;

    @Override
    public void sendFriendRequest(UserFriendsModel req) {
        UserFriendsEntity entity = UserFriendsMapper.toEntity(req);
        repository.save(entity);
    }

    @Override
    public void acceptFriendRequest(UserModel friend, UserModel user) {
        repository.changeRequestState(user.getUuid(), friend.getUuid(), FriendRequestState.ACCEPTED.name());
    }

    @Override
    public void declineFriendRequest(UserModel user, UserModel friend) {
        repository.deleteFromUserIdAndFriendId(user.getUuid(), friend.getUuid());
    }

    @Override
    public Boolean returnTrueIfAlreadyHasAcceptedOrSentFriendRequest(UserModel user, UserModel friend) {
        List<UserFriendsEntity> friends = repository.alreadyRequestedByUserIdAndFriendId
                (user.getUuid(), friend.getUuid());
        return (!friends.isEmpty());
    }

    @Override
    public UserFriendsModel findByUserAndFriend(UserModel user, UserModel friend) {
        UserFriendsEntity entity = repository.alreadyRequestedByUserIdAndFriendId(user.getUuid(), friend.getUuid()).getFirst();
        return UserFriendsMapper.toModel(entity);
    }

    @Override
    public Boolean validateIfFriendRequestIsPending(UserModel user, UserModel friend) {
        return repository.validateIfExistsByUserAndFriendAndState(user.getUuid(), friend.getUuid(), FriendRequestState.PENDING.name());
    }

    @Override
    public Boolean validateIfUserCanSendFriendRequest(UserModel user, UserModel friend){
        return repository.validateIfUserCanSendFriendRequestToTarget(user.getUuid(), friend.getUuid());
    }

    @Override
    public Boolean validateIfUserIsFriendOrFriended(UserModel user, UserModel friend) {
        return repository.validateIfAnyOfTheSidesAreBefriended(user.getUuid(), friend.getUuid());
    }

    @Override
    public List<UserModel> getUserFriends(UserModel user) {
        List<UserFriendsEntity> userFriends = repository.findUserFriendsByUserIdAndRequestState(user.getUuid(), FriendRequestState.ACCEPTED.name());

        List<UserModel> models = new ArrayList<>();
        for(UserFriendsEntity uf : userFriends){
            UserEntity u = uf.getFriend();

            models.add(UserMapper.toModel(u));
        }
        return models;
    }



    @Override
    public List<UserModel> getUserFriendsWithType(UserModel user, FriendRequestState state) {
        List<UserFriendsEntity> userFriends = repository.findUserFriendsByUserIdAndRequestState(user.getUuid(), state.name());

        List<UserModel> models = new ArrayList<>();
        for(UserFriendsEntity uf : userFriends){
            UserEntity u = uf.getFriend();

            models.add(UserMapper.toModel(u));
        }
        return models;
    }

    @Override
    public List<UserModel> getUserFriendsAndUserFriendedAs(UserModel user) {
        List<UserFriendsEntity> userFriends = repository.findUserFriendsAndUserFriended(user.getUuid());

        List<UserModel> models = new ArrayList<>();
        for(UserFriendsEntity uf : userFriends){
            UserEntity u = (uf.getFriend().getEmail().equals(user.getEmail()) ? uf.getPrincipalUser() : uf.getFriend());

            models.add(UserMapper.toModel(u));
        }
        return models;
    }
}
