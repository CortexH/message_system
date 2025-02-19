package com.Messaging_System.domain.service.user;

import com.Messaging_System.adapter.exception.CustomBadRequestException;
import com.Messaging_System.adapter.exception.CustomConflictException;
import com.Messaging_System.adapter.exception.CustomUnauthorizedException;
import com.Messaging_System.application.port.UserRepositoryPort;
import com.Messaging_System.domain.model.UserModel;
import com.Messaging_System.infrastructure.security.BCryptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserValidationService {

    private final BCryptService cryptService;
    private final UserRepositoryPort repository;

    public void validateUserCreation(UserModel userModel){
        if(repository.existsByEmail(userModel.getEmail())) throw new CustomConflictException("There is already an user with specified email.");
        if(!(userModel.getPassword().length() >= 8)) throw new CustomBadRequestException("Password length cannot be less than 8");
    }

    public UserModel validateUserLogin(String password, String email){
        UserModel userEntity = repository.findByEmail(email);
        if(userEntity == null || !cryptService.compare(password, userEntity.getPassword())) throw new CustomUnauthorizedException("Wrong password or mail");
        return userEntity;
    }
}
