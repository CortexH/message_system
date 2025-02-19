package com.Messaging_System.application.event.sentEvent;

import org.springframework.context.ApplicationEvent;

public class UserSentMessageEvent extends ApplicationEvent {



    public UserSentMessageEvent(Object source) {
        super(source);
    }
}
