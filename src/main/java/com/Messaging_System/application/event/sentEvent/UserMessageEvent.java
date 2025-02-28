package com.Messaging_System.application.event.sentEvent;

import com.Messaging_System.application.dto.input.WebsocketMessageDTO;
import com.Messaging_System.application.dto.internal.MessageWebsocketToServiceDTO;
import com.Messaging_System.domain.enums.WebsocketMessageResponseType;
import com.Messaging_System.domain.model.MessageModel;
import com.Messaging_System.domain.model.UserModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class UserMessageEvent extends ApplicationEvent {

    private UserModel sender;
    private MessageModel message;
    private WebsocketMessageResponseType type;

    public UserMessageEvent(
            Object source,
            UserModel uSender,
            MessageModel uMessage,
            WebsocketMessageResponseType uType

    ) {
        super(source);
        this.sender = uSender;
        this.message = uMessage;
        this.type = uType;
    }
}
