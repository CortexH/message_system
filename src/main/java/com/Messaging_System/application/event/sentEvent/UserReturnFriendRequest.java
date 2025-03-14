package com.Messaging_System.application.event.sentEvent;

import com.Messaging_System.application.dto.input.WebsocketFriendRequestDTO;
import com.Messaging_System.domain.model.UserModel;
import lombok.*;
import org.springframework.context.ApplicationEvent;


@Getter
@Setter
public class UserReturnFriendRequest extends ApplicationEvent {

    private UserModel sender;
    private WebsocketFriendRequestDTO data;

    public UserReturnFriendRequest(
            Object source,
            UserModel sender_,
            WebsocketFriendRequestDTO dto
    ) {
        super(source);
        this.sender = sender_;
        this.data = dto;
    }
}
