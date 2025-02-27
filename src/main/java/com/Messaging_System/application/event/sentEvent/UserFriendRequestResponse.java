package com.Messaging_System.application.event.sentEvent;

import com.Messaging_System.domain.enums.FriendRequestResponseType;
import com.Messaging_System.domain.model.UserModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class UserFriendRequestResponse extends ApplicationEvent {

    private UserModel requestSender;
    private UserModel requestReceiver;
    private FriendRequestResponseType requestState;

    public UserFriendRequestResponse(
            Object source, UserModel sender,
            UserModel receiver, FriendRequestResponseType state
    ) {
        super(source);
        this.requestSender = sender;
        this.requestReceiver = receiver;
        this.requestState = state;
    }
}
