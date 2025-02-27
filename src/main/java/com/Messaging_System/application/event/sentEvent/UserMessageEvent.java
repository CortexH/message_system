package com.Messaging_System.application.event.sentEvent;

import com.Messaging_System.application.dto.input.WebsocketMessageDTO;
import com.Messaging_System.domain.model.UserModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class UserMessageEvent extends ApplicationEvent {

    private UserModel sender;
    private WebsocketMessageDTO message;

    public UserMessageEvent(
            Object source,
            UserModel uSender,
            WebsocketMessageDTO uMessage
    ) {
        super(source);
        this.sender = uSender;
        this.message = uMessage;
    }
}
