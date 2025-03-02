package com.Messaging_System.application.dto.input;

import com.Messaging_System.domain.enums.WebsocketMessageResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WebsocketMessageDTO {

    @JsonProperty("type")
    private WebsocketMessageResponseType type;

    @JsonProperty("content")
    private String content;

    @JsonProperty("receiver_name")
    private String receiverName;

    @JsonProperty("message_id")
    private Long message_id;

    @JsonProperty("chatting")
    private String chatting_user;

    @JsonProperty("all_ids")
    private List<Long> message_ids;
}
