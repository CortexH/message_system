package com.Messaging_System.application.event.listeners;

import com.Messaging_System.application.dto.input.WebsocketMessageDTO;
import com.Messaging_System.application.event.sentEvent.User_MessageEvent;
import com.Messaging_System.application.service.websocket.WebsocketNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class HandleUserMessageEvent {

    private final WebsocketNotificationService notificationService;

    @EventListener
    public void messageEvent(User_MessageEvent event) throws IOException {
        WebsocketMessageDTO message = event.getMessage();

        switch (message.getType()){
            case SEND -> notificationService.sendUserMessage(message);
            case DELETE -> notificationService.deleteUserMessageNotification(message);
            case READ -> notificationService.readUserMessageNotification(message);
            case MARK_AS_NOT_READ -> notificationService.markAsNotReadUserMessageNotification(message);
            default -> {}
        }

    }

}
