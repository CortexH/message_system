package com.Messaging_System.application.dto.internal;

public record ChangeUserDTO(
        String name,
        String password,
        String email,
        String image,
        String bio
) {
}
