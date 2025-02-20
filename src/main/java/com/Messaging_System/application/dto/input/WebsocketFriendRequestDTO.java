package com.Messaging_System.application.dto.input;

import com.Messaging_System.domain.enums.FriendRequestType;
import com.fasterxml.jackson.annotation.JsonProperty;

public record WebsocketFriendRequestDTO(
        @JsonProperty("request_user_name")
        String requestedUserName,

        @JsonProperty("request_type")
        FriendRequestType type
) {
}
