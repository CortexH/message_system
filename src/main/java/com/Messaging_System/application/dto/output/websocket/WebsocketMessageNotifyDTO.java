package com.Messaging_System.application.dto.output.websocket;

import com.Messaging_System.application.dto.output.userDataDTO.FormattedUserDTO;
import com.Messaging_System.domain.enums.MessageState;
import com.Messaging_System.domain.enums.WebsocketMessageResponseType;
import com.Messaging_System.domain.enums.WebsocketResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WebsocketMessageNotifyDTO {

        @JsonProperty("type")
        WebsocketResponseType type;

        @JsonProperty("message_type")
        WebsocketMessageResponseType messageResponseType;

        @JsonProperty("content")
        String content;

        @JsonProperty("user_name")
        String userName;

        @JsonProperty("message_id")
        Long message_id;

}
