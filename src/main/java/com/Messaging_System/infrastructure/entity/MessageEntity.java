package com.Messaging_System.infrastructure.entity;

import com.Messaging_System.domain.enums.MessageState;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "messages")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

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

    @Column(name = "time_stamp")
    private LocalDateTime timestamp;
}
