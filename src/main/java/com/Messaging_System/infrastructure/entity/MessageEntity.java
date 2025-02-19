package com.Messaging_System.infrastructure.entity;

import com.Messaging_System.domain.enums.MessageState;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "messages")
@Builder
@Getter
@Setter
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "message_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "message_sender")
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name = "message_receiver")
    private UserEntity receiver;

    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "message_state")
    private MessageState messageState;
}
