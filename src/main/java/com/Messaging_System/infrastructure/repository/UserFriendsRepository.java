package com.Messaging_System.infrastructure.repository;

import com.Messaging_System.domain.enums.FriendRequestState;
import com.Messaging_System.infrastructure.entity.UserFriendsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserFriendsRepository extends JpaRepository<UserFriendsEntity, UUID> {
    @Query(nativeQuery = true,
            value = "SELECT uf.* FORM user_friends uf " +
                    "WHERE uf.principal_user_id = :id " +
                    "AND uf.friend_request_state = :state"

    )
    List<UserFriendsEntity> findUserFriendsByUserIdAndRequestState(
            @Param("id") UUID id,
            @Param("state") FriendRequestState state
            );

    @Query(nativeQuery = true,
            value = "SELECT uf.* FROM user_friends uf " +
                    "WHERE uf.principal_user_id = :userId " +
                    "AND uf.friend_user_id = :friendId " +
                    "AND uf.friend_request_state != 'DECLINED' "
    )
    List<UserFriendsEntity> alreadyRequestedByUserIdAndFriendId(
            @Param("userId") UUID userId,
            @Param("friendId") UUID friendId
    );

}
