package com.Messaging_System.infrastructure.entity;

import com.Messaging_System.domain.enums.UserRoles;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "tag")
    private String tag;

    private String password;
    private String email;
    private String userImage;
    private String userBio;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRoles role;
}
