package com.Messaging_System.infrastructure.repository;

import com.Messaging_System.infrastructure.entity.UserFriendsEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserFriendsRepository extends JpaRepository<UserFriendsEntity, UUID> {
    @Query(nativeQuery = true,
            value = "SELECT uf.* FROM user_friends uf " +
                    "WHERE uf.principal_user_id = :id " +
                    "AND uf.friend_request_state = :state"

    )
    List<UserFriendsEntity> findUserFriendsByUserIdAndRequestState(
            @Param("id") UUID id,
            @Param("state") String state
            );

    @Query(nativeQuery = true,
            value = "SELECT uf.* FROM user_friends uf " +
                    "WHERE (uf.principal_user_id = :userId " +
                    "AND uf.friend_user_id = :friendId) " +
                    "OR (uf.friend_user_id = :userId " +
                    "AND uf.principal_user_id = :friendId) " +
                    "AND uf.friend_request_state != 'DECLINED' "
    )
    List<UserFriendsEntity> alreadyRequestedByUserIdAndFriendId(
            @Param("userId") UUID userId,
            @Param("friendId") UUID friendId
    );

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = "UPDATE user_friends uf " +
                    "SET uf.friend_request_state = :new_state " +
                    "WHERE uf.principal_user_id = :userId " +
                    "AND uf.friend_user_id = :friendId"
    )
    void changeRequestState(
            @Param("userId") UUID userId,
            @Param("friendId") UUID friendId,
            @Param("new_state") String new_state
    );

    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) > 0 FROM user_friends uf " +
                    "WHERE uf.principal_user_id = :userId " +
                    "AND uf.friend_user_id = :friendId " +
                    "AND uf.friend_request_state = :state"
    )
    Boolean validateIfExistsByUserAndFriendAndState(
            @Param("userId") UUID userId,
            @Param("friendId") UUID friendId,
            @Param("state") String state
    );

    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) > 0 FROM user_friends uf " +
                    "WHERE (uf.principal_user_id = :userId " +
                    "AND uf.friend_user_id = :friendId) " +
                    "OR (uf.principal_user_id = :friendId " +
                    "AND uf.friend_user_id = :userId) "
    )
    Boolean validateIfAnyOfTheSidesAreBefriended(
            @Param("userId") UUID userId,
            @Param("friendId") UUID friendId
    );

    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) > 0 FROM user_friends uf " +
                    "WHERE (uf.principal_user_id = :userId " +
                    "AND uf.friend_user_id = :friendId) " +
                    "OR (uf.principal_user_id = :friendId " +
                    "AND uf.friend_user_id = :userId) " +
                    "AND uf.friend_request_state != 'PENDING'"
    )
    Boolean validateIfUserCanSendFriendRequestToTarget(
            @Param("userId") UUID userId,
            @Param("friendId") UUID friendId
    );

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "DELETE FROM user_friends uf " +
                    "WHERE uf.principal_user_id = :userId " +
                    "AND uf.friend_user_id = :friendId"
    )
    void deleteFromUserIdAndFriendId(
            @Param("userId") UUID userId,
            @Param("friendId") UUID friendId
    );

    @Query(nativeQuery = true,
            value = "SELECT uf.* FROM user_friends uf " +
                    "WHERE (uf.principal_user_id = :id " +
                    "OR uf.friend_user_id = :id) " +
                    "AND uf.friend_request_state = 'ACCEPTED'"

    )
    List<UserFriendsEntity> findUserFriendsAndUserFriended(
            @Param("id") UUID id
    );

}
