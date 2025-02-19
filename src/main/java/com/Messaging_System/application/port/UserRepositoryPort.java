package com.Messaging_System.application.port;


import com.Messaging_System.domain.model.UserModel;
import com.Messaging_System.infrastructure.entity.UserEntity;

import java.util.UUID;

public interface UserRepositoryPort {

    UserModel save(UserModel userEntity);
    void changeUserInformation(UserModel model);
    UserModel findByEmail(String email);
    Boolean existsByEmail(String email);
    UserModel findById(UUID id);
}
