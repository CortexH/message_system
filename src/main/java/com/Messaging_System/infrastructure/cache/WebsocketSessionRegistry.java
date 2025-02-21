package com.Messaging_System.infrastructure.cache;

import com.Messaging_System.domain.model.UserModel;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;

public class WebsocketSessionRegistry {

    public static final ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

}
