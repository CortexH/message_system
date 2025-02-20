package com.Messaging_System.domain.model;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    private UUID uuid;
    private String name;

    private String password;
    private String email;
    private String userImage;
    private String userBio;
    private String tag;

}
