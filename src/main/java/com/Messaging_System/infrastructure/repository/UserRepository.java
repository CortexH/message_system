package com.Messaging_System.infrastructure.repository;

import com.Messaging_System.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Boolean existsByEmail(String email);
    Boolean existsByEmailAndPassword(String email,String password);
    UserEntity findByEmail(String email);
}
