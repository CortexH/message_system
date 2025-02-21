package com.Messaging_System.application.service.websocket;

import com.Messaging_System.domain.model.UserModel;
import com.Messaging_System.infrastructure.cache.WebsocketSessionRegistry;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

@Service
public class WebsocketSessionService {

    public WebSocketSession getSessionByUser(UserModel user){
        return WebsocketSessionRegistry.sessions.get(user.getUuid().toString());
    }

    public void insertIntoSession(String key, WebSocketSession session){
        WebsocketSessionRegistry.sessions.put(key, session);
    }

    public void deleteFromSession(String key){
        WebsocketSessionRegistry.sessions.remove(key);
    }

}
