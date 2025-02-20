package com.Messaging_System.domain.service.user;

import com.Messaging_System.adapter.exception.CustomBadRequestException;
import com.Messaging_System.adapter.exception.CustomConflictException;
import com.Messaging_System.adapter.exception.CustomUnauthorizedException;
import com.Messaging_System.application.port.UserRepositoryPort;
import com.Messaging_System.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class UserValidationService {

    private final UserRepositoryPort repository;
    private final UserEncryptionService userEncryptionService;

    public void validateUserCreation(UserModel userModel){
        if(repository.existsByEmail(userModel.getEmail())) throw new CustomConflictException("There is already an user with specified email.");
        if(!(userModel.getPassword().length() >= 8)) throw new CustomBadRequestException("Password length cannot be less than 8");
    }

    public UserModel validateUserLogin(String password, String email){
        UserModel userEntity = repository.findByEmail(email);
        if(userEntity == null || !userEncryptionService.compareCredentialsToEncrypted(password, userEntity.getPassword())) throw new CustomUnauthorizedException("Wrong password or mail");
        return userEntity;
    }

    public void validateUserName(String username){



    }

    public void validateUserTag(String name, String tag){
        try{
            Integer.parseInt(tag);
        } catch (NumberFormatException e) {
            throw new CustomBadRequestException("username should have a tag");
        }
        if(tag.length() != 5){
            throw new CustomBadRequestException("user tag should have only 5 numbers");
        }

        List<String> usedTags = repository.findAllUsedTagsOfUsername(name);

        for(String thisTag : usedTags){
            if(thisTag.equals(tag)){
                throw new CustomConflictException("Specified tag is already in use");
            }
        }

    }


}
