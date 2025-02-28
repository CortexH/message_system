package com.Messaging_System.application.event.listeners;

import com.Messaging_System.application.dto.input.WebsocketMessageDTO;
import com.Messaging_System.application.dto.internal.MessageWebsocketToServiceDTO;
import com.Messaging_System.application.event.sentEvent.UserMessageEvent;
import com.Messaging_System.application.service.websocket.WebsocketNotificationService;
import com.Messaging_System.domain.enums.WebsocketMessageResponseType;
import com.Messaging_System.domain.model.MessageModel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class HandleUserMessageEvent {

    private final WebsocketNotificationService notificationService;

    @EventListener
    public void messageEvent(UserMessageEvent event) throws IOException {
        MessageModel message = event.getMessage();
        WebsocketMessageResponseType type = event.getType();

        switch (type){
            case SEND -> notificationService.sendUserMessage(message, event.getSender());
            case DELETE -> notificationService.deleteUserMessageNotification(
                    message,
                    event.getSender()
            );
            case READ -> notificationService.readUserMessageNotification(message);
            case MARK_AS_NOT_READ -> notificationService.markAsNotReadUserMessageNotification(message);
            default -> {}
        }

    }

}
