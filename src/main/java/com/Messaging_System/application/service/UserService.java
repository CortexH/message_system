package com.Messaging_System.application.service;

import com.Messaging_System.application.dto.input.UserLoginDTO;
import com.Messaging_System.application.dto.input.UserRegisterDTO;
import com.Messaging_System.application.dto.output.AuthenticatedDTO;
import com.Messaging_System.application.dto.output.userDataDTO.GetRecommendationTagDTO;
import com.Messaging_System.application.dto.output.userDataDTO.UserDataDTO;
import com.Messaging_System.application.port.UserRepositoryPort;
import com.Messaging_System.domain.model.UserModel;
import com.Messaging_System.domain.service.user.BusinessRuleApplicationService;
import com.Messaging_System.domain.service.user.UserAuthenticationService;
import com.Messaging_System.domain.service.user.UserEncryptionService;
import com.Messaging_System.domain.service.user.UserValidationService;
import com.Messaging_System.infrastructure.mapper.UserMapper;
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
    private final BusinessRuleApplicationService businessService;

    public AuthenticatedDTO registerNewUser(UserRegisterDTO data){
        UserModel user = UserModel.builder()
                .name(data.name())
                .email(data.email())
                .password(data.password())
                .tag(data.tag())
                .build();

        userValidationService.validateUserCreation(user);
        userValidationService.validateUserName(user.getName());
        userValidationService.validateUserTag(user.getName(), user.getTag());

        user = userEncryptionService.encryptUserCredentials(user);
        repositoryPort.save(user);

        return new AuthenticatedDTO(
                LocalDateTime.now(),
                200,
                userAuthenticationService.authenticateUser(user)
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

    public GetRecommendationTagDTO getRecommendedUserTag(String username){
        return new GetRecommendationTagDTO(
                businessService.findNonUsedNameTag(username)
        );
    }

    public UserModel findUserByFullUsername(String username){
        String name = businessService.getUserNameFromFullName(username);
        String tag = businessService.getUserTagFromFullName(username);

        return repositoryPort.findByUsernameAndTag(name, tag);
    }

    // shared methods
    public UserModel findUserById(UUID uuid){
        return repositoryPort.findById(uuid);
    }

}
