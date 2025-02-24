package com.Messaging_System.application.dto.input;

import com.Messaging_System.domain.enums.FriendRequestType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WebsocketFriendRequestDTO(
        @JsonProperty("request_user_name")
        String requestedUserName,

        @JsonProperty("sender_user_name")
        String senderUserName,

        @JsonProperty("request_type")
        FriendRequestType type
) {
}
