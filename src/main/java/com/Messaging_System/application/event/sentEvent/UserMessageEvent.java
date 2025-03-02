package com.Messaging_System.application.event.sentEvent;

import com.Messaging_System.domain.enums.WebsocketMessageResponseType;
import com.Messaging_System.domain.model.MessageModel;
import com.Messaging_System.domain.model.UserModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
@Setter
public class UserMessageEvent extends ApplicationEvent {

    private UserModel sender;
    private MessageModel message;
    private List<MessageModel> messageList;
    private WebsocketMessageResponseType type;

    public UserMessageEvent(
            Object source,
            UserModel uSender,
            MessageModel uMessage,
            WebsocketMessageResponseType uType,
            List<MessageModel> msgList

    ) {
        super(source);
        this.sender = uSender;
        this.message = uMessage;
        this.type = uType;
        this.messageList = msgList;
    }
}
