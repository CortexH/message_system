package com.Messaging_System.application.event.sentEvent;

import com.Messaging_System.domain.model.UserModel;
import lombok.*;
import org.springframework.context.ApplicationEvent;


@Getter
@Setter
public class User_ReturnFriendRequest extends ApplicationEvent {

    private UserModel sender;
    private UserModel target;

    public User_ReturnFriendRequest(
            Object source,
            UserModel sender_,
            UserModel target_
    ) {
        super(source);
        this.sender = sender_;
        this.target = target_;
    }



}
