package com.Messaging_System.infrastructure.repository;

import com.Messaging_System.infrastructure.entity.UserEntity;
import com.Messaging_System.infrastructure.entity.UserFriendsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserFriendsRepository extends JpaRepository<UserFriendsEntity, UUID> {

    @Query(nativeQuery = true,
            value = "SELECT uf.* FROM user_friends uf " +
                    "WHERE uf.principal_user_id = :userId"
    )
    List<UserFriendsEntity> findUserFriends(@Param("userId") UUID userId);

}
