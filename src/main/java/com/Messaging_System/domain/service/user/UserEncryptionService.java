package com.Messaging_System.domain.service.user;

import com.Messaging_System.domain.model.UserModel;
import com.Messaging_System.infrastructure.security.BCryptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserEncryptionService {

    private final BCryptService cryptService;


    public UserModel encryptUserCredentials(UserModel userEntity){
        userEntity.setPassword(cryptService.encode(userEntity.getPassword()));
        return userEntity;
    }

    public Boolean compareCredentialsToEncrypted(){
        return null; // aindan não foi utilizado
    }

}
