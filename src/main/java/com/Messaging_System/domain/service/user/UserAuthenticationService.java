package com.Messaging_System.domain.service.user;

import com.Messaging_System.domain.model.UserModel;
import com.Messaging_System.infrastructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthenticationService {

    private final JwtService jwtService;

    public String authenticateUser(UserModel userEntity){
        return jwtService.createJWT(userEntity.getEmail());
    }

}
