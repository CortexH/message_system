package com.Messaging_System.application.dto.output.websocket;

import com.Messaging_System.application.dto.output.userDataDTO.FormattedUserDTO;
import com.Messaging_System.domain.enums.FriendRequestResponseType;
import com.Messaging_System.domain.enums.WebsocketResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;

public record WebsocketFriendRequestNotify(

        @JsonProperty("type")
        WebsocketResponseType type,

        @JsonProperty("state")
        FriendRequestResponseType state,

        @JsonProperty("user_data")
        FormattedUserDTO user

) {
}
