package com.Messaging_System.domain.model;

import com.Messaging_System.domain.enums.MessageState;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageModel {

    private UUID id;

    private UserModel sender;
    private UserModel receiver;

    private String content;
    private MessageState messageState;

    private LocalDateTime timestamp;

}
