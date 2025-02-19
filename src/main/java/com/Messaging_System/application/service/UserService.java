package com.Messaging_System.application.service;

import com.Messaging_System.application.dto.input.UserLoginDTO;
import com.Messaging_System.application.dto.input.UserRegisterDTO;
import com.Messaging_System.application.dto.output.AuthenticatedDTO;
import com.Messaging_System.application.dto.output.userDataDTO.UserDataDTO;
import com.Messaging_System.application.port.UserRepositoryPort;
import com.Messaging_System.domain.model.UserModel;
import com.Messaging_System.domain.service.user.UserAuthenticationService;
import com.Messaging_System.domain.service.user.UserEncryptionService;
import com.Messaging_System.domain.service.user.UserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepositoryPort repositoryPort;
    private final UserEncryptionService userEncryptionService;
    private final UserValidationService userValidationService;
    private final UserAuthenticationService userAuthenticationService;

    public AuthenticatedDTO registerNewUser(UserRegisterDTO data){
        UserModel userEntity = UserModel.builder()
                .name(data.name())
                .email(data.email())
                .password(data.password())
                .build();

        userValidationService.validateUserCreation(userEntity);

        userEntity = userEncryptionService.encryptUserCredentials(userEntity);
        repositoryPort.save(userEntity);

        return new AuthenticatedDTO(
                LocalDateTime.now(),
                200,
                userAuthenticationService.authenticateUser(userEntity)
        );
    }

    public AuthenticatedDTO loginAsUser(UserLoginDTO data){
        UserModel model = userValidationService.validateUserLogin(data.password(), data.email());

        return new AuthenticatedDTO(
                LocalDateTime.now(),
                200,
                userAuthenticationService.authenticateUser(model)
        );
    }


    // shared methods
    public UserModel findUserById(UUID uuid){
        return repositoryPort.findById(uuid);
    }

}
