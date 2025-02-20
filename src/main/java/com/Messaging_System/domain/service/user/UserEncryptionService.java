package com.Messaging_System.domain.service.user;

import com.Messaging_System.domain.model.UserModel;
import com.Messaging_System.infrastructure.security.BCryptService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserEncryptionService {

    private final BCryptService cryptService;

    public UserModel encryptUserCredentials(UserModel userEntity){
        userEntity.setPassword(cryptService.encode(userEntity.getPassword()));
        return userEntity;
    }

    public Boolean compareCredentialsToEncrypted(String password, String encryptedPassword){
        return cryptService.compare(password, encryptedPassword);
    }

}
