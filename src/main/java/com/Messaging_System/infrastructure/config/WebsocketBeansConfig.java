package com.Messaging_System.infrastructure.config;

import com.Messaging_System.adapter.input.websocket.WebsocketHandler;
import com.Messaging_System.adapter.input.websocket.WebsocketInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebsocketBeansConfig {

    @Bean
    public WebsocketHandler websocketHandler(){
        return new WebsocketHandler();
    }

    @Bean
    public WebsocketInterceptor websocketInterceptor(){
        return new WebsocketInterceptor();
    }

}
