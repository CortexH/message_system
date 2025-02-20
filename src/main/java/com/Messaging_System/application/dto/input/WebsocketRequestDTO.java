package com.Messaging_System.application.dto.input;

import com.Messaging_System.domain.enums.WebsocketResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;

public record WebsocketRequestDTO(
        @JsonProperty("type")
        WebsocketResponseType type,

        @JsonProperty("friend_request")
        WebsocketFriendRequestDTO friendRequest,

        @JsonProperty("message")
        WebsocketMessageDTO message

) {
}
