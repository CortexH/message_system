package com.Messaging_System.application.sharedServices;

import com.Messaging_System.adapter.exception.CustomUnauthorizedException;
import com.Messaging_System.application.port.UserRepositoryPort;
import com.Messaging_System.domain.model.UserModel;
import com.Messaging_System.infrastructure.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserContextService {

    private final UserRepositoryPort repository;
    private final JwtService jwtService;

    public UserModel findUserByServletRequest(HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);

        return repository.findByEmail(jwtService.getJWTSubject(token));
    }

    public UserModel findUserByToken(String token){
        if(token == null) throw new CustomUnauthorizedException("Invalid token");
        //token = token.replace("bearer ", "");
        return repository.findByEmail(jwtService.getJWTSubject(token));
    }

    public Boolean validateUserToken(String token){
        String email = jwtService.getJWTSubject(token);
        return repository.existsByEmail(email);
    }

}
