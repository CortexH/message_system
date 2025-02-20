package com.Messaging_System.application.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WebsocketFriendRequestDTO(
        @JsonProperty("request_user_id")
        String requestedUserName
) {
}
