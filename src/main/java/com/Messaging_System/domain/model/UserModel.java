package com.Messaging_System.domain.model;

import com.Messaging_System.domain.enums.UserRoles;
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

    private UserRoles role;
}
