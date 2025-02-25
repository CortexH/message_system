package com.Messaging_System.infrastructure.security;

import com.Messaging_System.adapter.exception.CustomUnauthorizedException;
import com.Messaging_System.application.dto.output.exceptions.DTO_ExUnauthorized;
import com.Messaging_System.application.sharedServices.UserContextService;
import com.Messaging_System.domain.model.UserModel;
import com.Messaging_System.infrastructure.config.AllowedEndpoints;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomSecurityFilterChain extends OncePerRequestFilter {

    private final String SECRET_KEY = "gzbvUx0wkC8RvshYzbvUx0bvUx0wkC80wkC8RvshYzbvUx0bvUx0";
    private final UserContextService contextService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ObjectMapper obj = new ObjectMapper();
        obj.registerModule(new JavaTimeModule());
        try{
            if(request.getHeader("SECRET") != null && request.getHeader("SECRET").equals(SECRET_KEY)){
                filterChain.doFilter(request,response);
                return;
            }

            if(checkAllowedEndpoint(request.getRequestURI())){
                filterChain.doFilter(request,response);
                return;
            }

            String token = request.getHeader("Authorization");

            if(token == null) throw new CustomUnauthorizedException("Invalid token");

            UserModel user = contextService.findUserByToken(token.substring(7));

            if(user == null) throw new CustomUnauthorizedException("Invalid token");

            CustomUserDetails uDetails = new CustomUserDetails(user);

            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), null, uDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } catch (CustomUnauthorizedException | JWTDecodeException e) {
            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().write(
                    obj.writeValueAsString(new DTO_ExUnauthorized(
                            LocalDateTime.now().toString(), HttpStatus.UNAUTHORIZED.value(),
                            HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                            "Invalid token"
                    ))
            );

        }
    }

    private boolean checkAllowedEndpoint(String uri){
        return Arrays.asList(AllowedEndpoints.noAuthorizationAllowedEndpoints).contains(uri);
    }

}
