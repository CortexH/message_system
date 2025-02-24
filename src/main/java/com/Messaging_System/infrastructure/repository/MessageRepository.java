package com.Messaging_System.infrastructure.repository;

import com.Messaging_System.infrastructure.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
}
