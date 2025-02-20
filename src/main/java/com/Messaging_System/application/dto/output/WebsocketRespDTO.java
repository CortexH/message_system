package com.Messaging_System.application.dto.output;

import com.Messaging_System.application.dto.output.userDataDTO.FormattedUserDTO;
import com.Messaging_System.application.dto.output.userDataDTO.FriendMessageSentDTO;
import com.Messaging_System.domain.enums.WebsocketResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;

public record WebsocketRespDTO(
        @JsonProperty("type")
        WebsocketResponseType type,
        @JsonProperty("friend_request")
        FormattedUserDTO friend,
        @JsonProperty("message")
        FriendMessageSentDTO message

) {
}
