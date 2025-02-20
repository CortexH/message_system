package com.Messaging_System.infrastructure.repository;

import com.Messaging_System.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Boolean existsByEmail(String email);
    Boolean existsByEmailAndPassword(String email,String password);
    UserEntity findByEmail(String email);

    @Query(nativeQuery = true,
            value = "SELECT u.tag FROM users u " +
                    "WHERE u.name LIKE :name"
    )
    List<String> findAllSimilarNamesTags(@Param("name") String name);

    @Query(nativeQuery = true,
            value = "SELECT u.* FROM users u " +
                    "WHERE u.name = ':name' " +
                    "AND u.tag = ':tag'"
    )
    Boolean existByUsernameAndTag(
            @Param("name") String name,
            @Param("tag") String tag
    );

    @Query(nativeQuery = true,
            value = "SELECT u.* FROM users u " +
                    "WHERE u.name = :name " +
                    "AND u.tag = :tag"
    )
    Optional<UserEntity> findByUsernameAndTag(
            @Param("name") String name,
            @Param("tag") String tag
    );

}
