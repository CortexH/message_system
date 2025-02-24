package com.Messaging_System.infrastructure.config;


import com.Messaging_System.infrastructure.security.CustomSecurityFilterChain;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final CustomSecurityFilterChain customSecurityFilterChain;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(i -> i
                        .requestMatchers(AllowedEndpoints.userAllowedEndpoints).hasAnyRole("ADMIN", "USER")
                        .requestMatchers(AllowedEndpoints.onlyForAdmsEndpoints).hasRole("ADMIN")
                        .requestMatchers(AllowedEndpoints.noAuthorizationAllowedEndpoints).permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .headers(header -> header.frameOptions(frame -> frame.disable()))
                .addFilterBefore(customSecurityFilterChain, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(handle -> handle.accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.getWriter().write("cannot access this.");
                    response.getWriter().flush();
                }))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
