package com.Messaging_System.application.dto.output.websocket;

import com.Messaging_System.application.dto.output.userDataDTO.FormattedUserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public record WebsocketFriendRequestNotify(
        @JsonProperty("user")
        FormattedUserDTO user

) {
}
