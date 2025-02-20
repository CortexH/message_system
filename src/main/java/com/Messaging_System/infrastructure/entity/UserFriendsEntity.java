package com.Messaging_System.infrastructure.entity;

import com.Messaging_System.domain.enums.FriendRequestState;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "user_friends")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFriendsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID user_friends_id;

    @ManyToOne
    @JoinColumn(name = "principal_user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "friend_user_id")
    private UserEntity friend;

    @Enumerated(EnumType.STRING)
    @Column(name = "friend_request_state")
    private FriendRequestState state;

}
