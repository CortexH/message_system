package com.Messaging_System.application.event.listeners;

import com.Messaging_System.application.event.sentEvent.UserSentMessageEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MessageEventListener {

    @EventListener
    public void handleUserMessageSent(UserSentMessageEvent event){

    }

}
