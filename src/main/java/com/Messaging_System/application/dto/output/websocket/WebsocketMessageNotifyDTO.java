package com.Messaging_System.application.dto.output.websocket;

import com.Messaging_System.application.dto.output.userDataDTO.FormattedUserDTO;
import com.Messaging_System.domain.enums.MessageState;
import com.Messaging_System.domain.enums.WebsocketResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;

public record WebsocketMessageNotifyDTO(
        @JsonProperty("type")
        WebsocketResponseType type,

        @JsonProperty("content")
        String content,

        @JsonProperty("user_name")
        String userName
) {

}
