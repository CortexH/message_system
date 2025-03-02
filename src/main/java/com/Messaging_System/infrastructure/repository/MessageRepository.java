package com.Messaging_System.infrastructure.repository;

import com.Messaging_System.infrastructure.entity.MessageEntity;
import com.Messaging_System.infrastructure.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM messages m " +
                    "WHERE ((m.message_sender = :userId " +
                    "AND m.message_receiver = :friendId) " +
                    "OR (m.message_sender = :friendId " +
                    "AND m.message_receiver = :userId)) " +
                    "AND m.message_state != 'DELETED' " +
                    "ORDER BY m.time_stamp asc " +
                    "LIMIT 50 " +
                    "OFFSET :lastIndex"
    )
    List<MessageEntity> get50LastsMessagesFromUserAndOtherUser(
            @Param("userId") UUID userId,
            @Param("friendId") UUID friendId,
            @Param("lastIndex") Integer lastIndex
    );

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "UPDATE messages m " +
                    "SET m.message_state = 'READ' " +
                    "WHERE m.message_id IN (:ids)"
    )
    void readMessagesByIdList(
            @Param("ids") List<Long> ids
    );

}
