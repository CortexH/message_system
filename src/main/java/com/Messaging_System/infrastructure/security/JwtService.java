package com.Messaging_System.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
public class JwtService {

    private final String SECRET = "SUPER DUPPER MEGA INSANE EPSUM MAGNIFIC SECRET KEY";
    private final Algorithm algorithm = Algorithm.HMAC256(SECRET);
    private final String ISSUER = "Messaging system - By Henrique Silveira Soares.";

    private final Long EXPIRATION_DAYS = 30L;

    public String createJWT(String subject){
        return JWT.create()
                .withSubject(subject)
                .withExpiresAt(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(EXPIRATION_DAYS).toInstant())
                .withIssuedAt(Instant.now())
                .withIssuer(ISSUER)
                .sign(algorithm);
    }

    public String getJWTSubject(String token){
        return JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build()
                .verify(token)
                .getSubject();
    }

}
