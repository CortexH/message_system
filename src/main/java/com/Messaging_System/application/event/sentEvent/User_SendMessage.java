package com.Messaging_System.application.event.sentEvent;

import com.Messaging_System.domain.model.UserModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class User_SendMessage extends ApplicationEvent {

    private UserModel sender;
    private UserModel target;
    private String content;

    public User_SendMessage(
            Object source,
            UserModel uSender,
            UserModel uTarget,
            String msgContent
    ) {
        super(source);
        this.target = uTarget;
        this.sender = uSender;
        this.content = msgContent;
    }
}
