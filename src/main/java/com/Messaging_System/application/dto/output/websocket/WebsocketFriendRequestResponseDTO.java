package com.Messaging_System.application.dto.output.websocket;

import com.Messaging_System.domain.enums.FriendRequestResponseType;
import com.Messaging_System.domain.enums.WebsocketResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;

public record WebsocketFriendRequestResponseDTO(
        @JsonProperty("type")
        WebsocketResponseType type,

        @JsonProperty("state")
        FriendRequestResponseType state,

        @JsonProperty("request_sender")
        String sender,

        @JsonProperty("friend")
        String receiver

) {
}
